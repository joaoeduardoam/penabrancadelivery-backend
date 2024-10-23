package com.joaoeduardo.penabrancadelivery_backend.domain.user.exception;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(String message){

        super(message);

    }
}
