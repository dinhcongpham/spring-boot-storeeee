package com.jokerP.store.payments.paypal;

import com.jokerP.store.orders.Order;
import com.jokerP.store.payments.PaymentGateway;
import com.jokerP.store.payments.dtos.CheckoutSession;
import com.jokerP.store.payments.dtos.PaymentResult;
import com.jokerP.store.payments.dtos.WebhookRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PaypalPaymentGateway implements PaymentGateway {

    @Override
    public CheckoutSession createCheckoutSession(Order order) {
        String fakePaypalUrl = "https://paypal.com/checkout?orderId=" + order.getId();
        return new CheckoutSession(fakePaypalUrl);
    }

    @Override
    public Optional<PaymentResult> parseWebhookRequest(WebhookRequest request) {
        return Optional.empty();
    }
}
