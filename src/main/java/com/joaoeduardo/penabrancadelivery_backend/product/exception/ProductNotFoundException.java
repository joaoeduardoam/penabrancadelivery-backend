package com.joaoeduardo.penabrancadelivery_backend.product.exception;

public class ProductNotFoundException extends RuntimeException{

    public ProductNotFoundException(String message){

        super(message);

    }
}
