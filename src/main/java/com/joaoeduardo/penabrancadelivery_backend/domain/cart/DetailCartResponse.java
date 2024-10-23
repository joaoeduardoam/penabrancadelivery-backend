package com.joaoeduardo.penabrancadelivery_backend.domain.cart;


import com.joaoeduardo.penabrancadelivery_backend.domain.product.ProductCart;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Builder
@Data
public class DetailCartResponse {

    List<ProductCart> products;

}
