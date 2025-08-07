package com.jokerP.store.payments.dtos;

import com.jokerP.store.payments.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class PaymentResult {
    private Long orderId;
    private PaymentStatus paymentStatus;
}
