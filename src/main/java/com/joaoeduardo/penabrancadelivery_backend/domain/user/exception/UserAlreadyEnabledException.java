package com.joaoeduardo.penabrancadelivery_backend.domain.user.exception;

public class UserAlreadyEnabledException extends RuntimeException{

    public UserAlreadyEnabledException(String message){

        super(message);

    }
}
