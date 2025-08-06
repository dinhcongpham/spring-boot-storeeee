package com.jokerP.store.payments;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class PaymentResult {
    private Long orderId;
    private PaymentStatus paymentStatus;
}
