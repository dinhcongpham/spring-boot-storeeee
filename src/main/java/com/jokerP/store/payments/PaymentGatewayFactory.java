package com.jokerP.store.payments;

import com.jokerP.store.payments.paypal.PaypalPaymentGateway;
import com.jokerP.store.payments.stripe.StripePaymentGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentGatewayFactory {
    private final StripePaymentGateway stripe;
    private final PaypalPaymentGateway paypal;

    public PaymentGateway get(PaymentProvider paymentProvider) {
        return switch (paymentProvider) {
                    case PAYPAL -> paypal;
                    case STRIPE -> stripe;
                };
    }
}
