package com.joaoeduardo.penabrancadelivery_backend.security;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record AuthRequest(
        @NotNull(message = "Email field can't be null!")
        @NotBlank(message = "Email field can't be empty!")
        @Email(message = "Provide a valid email!")
        String email,

        @NotNull(message = "Password field can't be null!")
        @NotBlank(message = "Password field can't be empty!")
        @Size(min = 8, message = "Password must have at least 8 characters!")
        String password
) {
}
