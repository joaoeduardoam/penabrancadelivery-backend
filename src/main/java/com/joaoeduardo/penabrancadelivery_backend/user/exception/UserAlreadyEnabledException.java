package com.joaoeduardo.penabrancadelivery_backend.user.exception;

public class UserAlreadyEnabledException extends RuntimeException{

    public UserAlreadyEnabledException(String message){

        super(message);

    }
}
