package com.jokerP.store.payments;

import com.jokerP.store.orders.OrderRepository;
import com.jokerP.store.payments.dtos.CheckoutRequest;
import com.jokerP.store.payments.dtos.CheckoutResponse;
import com.jokerP.store.payments.dtos.WebhookRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("checkout")
public class CheckoutController {
    private final CheckoutService checkoutService;
    private final OrderRepository orderRepository;
    @PostMapping
    public CheckoutResponse checkout(@Valid @RequestBody CheckoutRequest checkoutRequest) {
        return checkoutService.checkout(checkoutRequest);
    }

    @PostMapping("/webhook")
    public void handleWebhook(
            @RequestHeader Map<String, String> headers,
            @RequestBody String payload
    ) {
        checkoutService.handleWebhookEvent(new WebhookRequest(headers, payload));
    }
}
