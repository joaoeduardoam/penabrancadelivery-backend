package com.joaoeduardo.penabrancadelivery_backend.product;

import com.joaoeduardo.penabrancadelivery_backend.user.User;
import com.joaoeduardo.penabrancadelivery_backend.user.UserCreateDTO;
import com.joaoeduardo.penabrancadelivery_backend.user.UserResponse;
import com.joaoeduardo.penabrancadelivery_backend.user.UserService;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ProductResponse> registerProduct (@RequestBody @Valid ProductCreateDTO productCreateDTO) {

        Product savedProduct = productService.registerProduct(new Product(productCreateDTO));

        return ResponseEntity.ok(new ProductResponse(savedProduct));

    }

    @PutMapping("/{productId}")
    public ResponseEntity<ProductResponse> updateProduct (@RequestBody @Valid ProductUpdateDTO productUpdateDTO,@PathVariable UUID productId) {

        Product updatedProduct = productService.updateProduct(productId, productUpdateDTO);

        return ResponseEntity.ok(new ProductResponse(updatedProduct));

    }


    @GetMapping("/{productId}")
    public ResponseEntity<ProductResponse> findProductById (@PathVariable UUID productId){

        Product product = productService.findProductById(productId);

        return ResponseEntity.ok(new ProductResponse(product));

    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> findAllProducts (){


        List<Product> products = productService.findAllProducts();

        return ResponseEntity.ok(products.stream().map(ProductResponse::new).toList());

    }




}
