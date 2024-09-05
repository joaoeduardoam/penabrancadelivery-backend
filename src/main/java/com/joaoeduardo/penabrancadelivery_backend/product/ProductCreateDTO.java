package com.joaoeduardo.penabrancadelivery_backend.product;

import com.joaoeduardo.penabrancadelivery_backend.user.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record ProductCreateDTO(
        @NotNull(message = "Title field can't be null!")
        @NotBlank(message = "Title field can't be empty!")
        String title,
        @NotNull(message = "Description field can't be null!")
        @NotBlank(message = "Description field can't be empty!")
        String description,
        @NotNull(message = "Price field can't be null!")
//        @NotBlank(message = "Price field can't be empty!")
        BigDecimal price,
        @NotNull(message = "Image field can't be null!")
        @NotBlank(message = "Image field can't be empty!")
        String image
){

}
