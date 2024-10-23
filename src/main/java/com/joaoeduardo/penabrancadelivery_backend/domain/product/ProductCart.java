package com.joaoeduardo.penabrancadelivery_backend.domain.product;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "cart_products")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductCart {

    @NotNull
    private UUID productId;

    @NotNull
    private String title;

    @NotNull
    private BigDecimal price;

    @NotNull
    private String image;

    public ProductCart (Product product){
        this.productId = product.getId();
        this.title = product.getTitle();
        this.price = product.getPrice();
        this.image = product.getImage();
    }



}
