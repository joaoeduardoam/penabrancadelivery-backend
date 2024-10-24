package com.joaoeduardo.penabrancadelivery_backend.domain.product;

import com.joaoeduardo.penabrancadelivery_backend.domain.product.exception.ProductNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductService {


    private final ProductRepository productRepository;

    public ProductResponse registerProduct(Product product){

        Product savedProduct = productRepository.save(product);

        return new ProductResponse(savedProduct);

    }


    public ProductResponse findProductById(UUID productId) {

        Optional<Product> product = productRepository.findById(productId);

        if (product.isEmpty()){
            throw new ProductNotFoundException("Product not found with ID: "+productId);
        }

        Product rawProduct = product.get();

        return new ProductResponse(rawProduct);
    }

    public ProductResponse updateProduct(UUID productId, ProductUpdateDTO productUpdateDTO) {

        Optional<Product> product = productRepository.findById(productId);

        if (product.isEmpty()){
            throw new ProductNotFoundException("Product not found with ID: "+productId);
        }

        Product rawProduct = product.get();

        rawProduct.updateData(productUpdateDTO);

        Product updatedProduct = productRepository.save(rawProduct);

        return new ProductResponse(updatedProduct);
    }

    public List<ProductResponse> findAllProducts() {

        return  productRepository.findAll().stream().map(ProductResponse::new).toList();

    }

    public List<ProductResponse> findAllByIdIn(List<UUID> idList) {

        return productRepository.findAllByIdIn(idList).stream().map(ProductResponse::new).toList();

    }


    public void deleteProductById(UUID productId) {

        Optional<Product> product = productRepository.findById(productId);

        if (product.isPresent()) {
            productRepository.deleteById(productId);
        } else {
            throw new ProductNotFoundException("Product not found with ID: "+productId);
        }


    }
}
