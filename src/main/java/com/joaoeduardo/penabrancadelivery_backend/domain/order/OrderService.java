package com.joaoeduardo.penabrancadelivery_backend.domain.order;

import com.joaoeduardo.penabrancadelivery_backend.domain.cart.DetailCartItemRequest;
import com.joaoeduardo.penabrancadelivery_backend.domain.cart.DetailCartRequest;
import com.joaoeduardo.penabrancadelivery_backend.domain.cart.DetailCartResponse;
import com.joaoeduardo.penabrancadelivery_backend.domain.cart.service.CartReader;
import com.joaoeduardo.penabrancadelivery_backend.domain.product.ProductResponse;
import com.joaoeduardo.penabrancadelivery_backend.domain.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class OrderService {


    private final ProductService productService;
    private final CartReader cartReader;


    public DetailCartResponse getCartDetails(DetailCartRequest detailCartRequest) {

        List<UUID> productsIds = detailCartRequest.cartItemRequests().stream().map(DetailCartItemRequest::productId).toList();

        List<ProductResponse> productsInformation = productService.findAllByIdIn(productsIds);

        return cartReader.getDetails(productsInformation);

    }

}
