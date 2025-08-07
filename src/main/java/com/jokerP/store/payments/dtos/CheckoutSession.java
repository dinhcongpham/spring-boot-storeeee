package com.jokerP.store.payments.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CheckoutSession {
    private String checkoutUrl;
}
