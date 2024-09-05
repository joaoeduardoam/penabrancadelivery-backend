package com.joaoeduardo.penabrancadelivery_backend.product;

import com.joaoeduardo.penabrancadelivery_backend.product.exception.ProductNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductService {


    private final ProductRepository productRepository;

    public Product registerProduct(Product product){

        Product savedProduct = productRepository.save(product);

        return savedProduct;

    }


    public Product findProductById(UUID productId) {

        Optional<Product> product = productRepository.findById(productId);

        if (product.isEmpty()){
            throw new ProductNotFoundException("Product not found with ID: "+productId);
        }

        Product rawProduct = product.get();

        return rawProduct;
    }

    public Product updateProduct(UUID productId, ProductUpdateDTO productUpdateDTO) {

        Optional<Product> product = productRepository.findById(productId);

        if (product.isEmpty()){
            throw new ProductNotFoundException("Product not found with ID: "+productId);
        }

        Product rawProduct = product.get();

        rawProduct.updateData(productUpdateDTO);

        Product updatedProduct = productRepository.save(rawProduct);

        return updatedProduct;
    }

    public List<Product> findAllProducts() {

        return  productRepository.findAll();

    }
}
