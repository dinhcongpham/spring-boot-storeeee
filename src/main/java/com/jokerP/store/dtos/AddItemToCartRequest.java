package com.jokerP.store.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AddItemToCartRequest {
    @NotBlank(message = "ProductId is required")
    private Long productId;
}
