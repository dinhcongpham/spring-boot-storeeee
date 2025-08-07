package com.jokerP.store.payments;

import com.jokerP.store.orders.Order;
import com.jokerP.store.payments.dtos.CheckoutSession;
import com.jokerP.store.payments.dtos.PaymentResult;
import com.jokerP.store.payments.dtos.WebhookRequest;

import java.util.Optional;

public interface PaymentGateway {
    CheckoutSession createCheckoutSession(Order order);
    Optional<PaymentResult> parseWebhookRequest(WebhookRequest request);
}
