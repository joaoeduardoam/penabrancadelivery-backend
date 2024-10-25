package com.joaoeduardo.penabrancadelivery_backend.domain.cart.service;

import com.joaoeduardo.penabrancadelivery_backend.domain.cart.DetailCartResponse;
import com.joaoeduardo.penabrancadelivery_backend.domain.product.Product;
import com.joaoeduardo.penabrancadelivery_backend.domain.product.ProductCart;
import com.joaoeduardo.penabrancadelivery_backend.domain.product.ProductResponse;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@NoArgsConstructor
public class CartReader {

    public DetailCartResponse getDetails(List<ProductResponse> products){

        List<ProductCart> cartProducts = products.stream().map(ProductCart::new).toList();
        return DetailCartResponse.builder()
                .productsCart(cartProducts)
                .build();

    }
}
