package com.joaoeduardo.penabrancadelivery_backend.domain.product;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductCart {

    @NotNull
    private UUID id;

    @NotNull
    private String title;

    @NotNull
    private BigDecimal price;

    @NotNull
    private String image;

    public ProductCart (ProductResponse product){
        this.id = product.id();
        this.title = product.title();
        this.price = product.price();
        this.image = product.image();
    }



}
