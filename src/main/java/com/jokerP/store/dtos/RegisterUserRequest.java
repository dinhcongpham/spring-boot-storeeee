package com.jokerP.store.dtos;

import com.jokerP.store.validation.Lowercase;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.aspectj.bridge.IMessage;

@Data
public class RegisterUserRequest {
    @NotBlank(message = "Name is required")
    @Size(max = 255, message = "Name must must be less than 255 characters")
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Email must be valid")
    @Lowercase(message = "Email must be lowercase")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 8, max = 25, message = "Password must be at least 8 characters")
    private String password;
}
