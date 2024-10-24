package com.joaoeduardo.penabrancadelivery_backend.domain.product;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductCartResponse(UUID id, String title, BigDecimal price, String image) {
    ProductCartResponse(ProductCart productCart){
        this(productCart.getId(), productCart.getTitle(), productCart.getPrice(), productCart.getImage());
    }
}
