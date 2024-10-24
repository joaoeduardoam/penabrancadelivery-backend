package com.joaoeduardo.penabrancadelivery_backend.domain.order;

import com.joaoeduardo.penabrancadelivery_backend.domain.cart.DetailCartItemRequest;
import com.joaoeduardo.penabrancadelivery_backend.domain.cart.DetailCartRequest;
import com.joaoeduardo.penabrancadelivery_backend.domain.cart.DetailCartResponse;
import com.joaoeduardo.penabrancadelivery_backend.domain.product.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final ProductService productService;
    private final OrderService orderService;

    @GetMapping
    public ResponseEntity<DetailCartResponse> getCartDetails (@RequestBody @Valid List<UUID> productIds){

        List<DetailCartItemRequest> cartItemRequests = productIds.stream()
                .map(uuid -> new DetailCartItemRequest(uuid, 1))
                .toList();

        DetailCartRequest detailCartRequest = DetailCartRequest.builder().cartItemRequests(cartItemRequests).build();

        DetailCartResponse cartDetails = orderService.getCartDetails(detailCartRequest);

        return ResponseEntity.ok(cartDetails);

    }




}
