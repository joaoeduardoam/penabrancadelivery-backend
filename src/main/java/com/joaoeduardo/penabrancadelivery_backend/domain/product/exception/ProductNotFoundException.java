package com.joaoeduardo.penabrancadelivery_backend.domain.product.exception;

public class ProductNotFoundException extends RuntimeException{

    public ProductNotFoundException(String message){

        super(message);

    }
}
