package com.joaoeduardo.penabrancadelivery_backend.domain.product;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ProductResponse> registerProduct (@RequestBody @Valid ProductCreateDTO productCreateDTO) {

        ProductResponse savedProduct = productService.registerProduct(new Product(productCreateDTO));

        return ResponseEntity.ok(savedProduct);

    }

    @PutMapping("/{productId}")
    public ResponseEntity<ProductResponse> updateProduct (@RequestBody @Valid ProductUpdateDTO productUpdateDTO,@PathVariable UUID productId) {

        ProductResponse updatedProduct = productService.updateProduct(productId, productUpdateDTO);

        return ResponseEntity.ok(updatedProduct);

    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<List<ProductResponse>> deleteProduct (@PathVariable UUID productId) {

        productService.deleteProductById(productId);

        return ResponseEntity.ok(productService.findAllProducts());

    }


    @GetMapping("/{productId}")
    public ResponseEntity<ProductResponse> findProductById (@PathVariable UUID productId){

        ProductResponse product = productService.findProductById(productId);

        return ResponseEntity.ok(product);

    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> findAllProducts (){


        List<ProductResponse> products = productService.findAllProducts();

        return ResponseEntity.ok(products);

    }




}
