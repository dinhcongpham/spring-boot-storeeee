package com.jokerP.store.payments.dtos;

import com.jokerP.store.payments.PaymentProvider;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class CheckoutRequest {
    @NotNull(message = "Cart Id is required.")
    private UUID cartId;

    @NotNull(message = "Payment provider is required.")
    private PaymentProvider provider = PaymentProvider.STRIPE;
}
