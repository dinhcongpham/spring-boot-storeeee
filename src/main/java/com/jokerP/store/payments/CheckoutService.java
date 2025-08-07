package com.jokerP.store.payments;


import com.jokerP.store.orders.Order;
import com.jokerP.store.commons.BadRequestException;
import com.jokerP.store.commons.NotFoundException;
import com.jokerP.store.carts.CartRepository;
import com.jokerP.store.orders.OrderRepository;
import com.jokerP.store.auth.AuthService;
import com.jokerP.store.carts.CartService;
import com.jokerP.store.payments.dtos.CheckoutRequest;
import com.jokerP.store.payments.dtos.CheckoutResponse;
import com.jokerP.store.payments.dtos.WebhookRequest;
import com.jokerP.store.util.Constants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CheckoutService {
    private final CartRepository cartRepository;
    private final OrderRepository orderRepository;
    private final CartService cartService;
    private final AuthService authService;
    private final PaymentGatewayFactory gatewayFactory;

    @Transactional
    public CheckoutResponse checkout(CheckoutRequest checkoutRequest) {
        var cart = cartRepository.getCartWithItems(checkoutRequest.getCartId()).orElse(null);
        if (cart == null) {
            throw new NotFoundException(Constants.Message.CART_NOT_FOUND);
        }

        if (cart.isEmpty()) {
            throw new BadRequestException(Constants.Message.CART_EMPTY);
        }

        var order = Order.fromCart(cart, authService.getCurrentUser());

        orderRepository.save(order);

        try {
            var gateway = gatewayFactory.get(checkoutRequest.getProvider());
            var session = gateway.createCheckoutSession(order);
            cartService.clearCart(cart.getId());

            return new CheckoutResponse(order.getId(), session.getCheckoutUrl());
        } catch (PaymentException e) {  
            orderRepository.delete(order);
            throw e;
        }
    }

    public void handleWebhookEvent(WebhookRequest request) {
        PaymentGateway gateway = gatewayFactory.get(PaymentProvider.STRIPE);
        gateway
            .parseWebhookRequest(request)
            .ifPresent(paymentResult -> {
                var order = orderRepository.findById(paymentResult.getOrderId()).orElseThrow();
                order.setStatus(paymentResult.getPaymentStatus());
                orderRepository.save(order);
            });
    }
}
