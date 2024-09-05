package com.joaoeduardo.penabrancadelivery_backend.product;

import com.joaoeduardo.penabrancadelivery_backend.user.User;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductResponse(UUID id, String title, String description, BigDecimal price, String image) {
    ProductResponse(Product product){
        this(product.getId(), product.getTitle(), product.getDescription(), product.getPrice(), product.getImage());
    }
}
