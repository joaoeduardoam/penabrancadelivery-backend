package com.joaoeduardo.penabrancadelivery_backend.user.exception;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(String message){

        super(message);

    }
}
