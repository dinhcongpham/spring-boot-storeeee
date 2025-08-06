package com.jokerP.store.dtos;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NonNull;
import org.aspectj.bridge.IMessage;

@Data
public class UpdateCartItemRequest {
    @NotNull(message = "Quantity is required")
    @Min(value = 1, message = "Quantity must be greater than zero")
    @Max(value = 1000, message = "Quantity must be lower than thousand")
    private Integer quantity;
}
