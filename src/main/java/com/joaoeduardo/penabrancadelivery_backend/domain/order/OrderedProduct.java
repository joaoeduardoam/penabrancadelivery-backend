package com.joaoeduardo.penabrancadelivery_backend.domain.order;

import com.joaoeduardo.penabrancadelivery_backend.domain.product.Product;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;

import java.math.BigDecimal;
import java.util.UUID;

//@Entity
//@Table(name = "ordered_products")
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
@Builder
public class OrderedProduct {

//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotNull
    private UUID productId;

    @NotNull
    private String productTitle;

    @Positive
    private BigDecimal price;

    @Positive
    private long quantity;

    private String stripeSessionId;


    public static OrderedProduct create(long quantity, Product product){

        return OrderedProduct.builder()
                .id(UUID.randomUUID())
                .productId(product.getId())
                .productTitle(product.getTitle())
                .price(product.getPrice())
                .quantity(quantity)
                .build();
    }


}