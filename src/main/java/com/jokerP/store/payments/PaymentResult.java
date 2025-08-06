package com.jokerP.store.payments;

import com.jokerP.store.entities.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class PaymentResult {
    private Long orderId;
    private PaymentStatus paymentStatus;
}
