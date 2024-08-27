package com.joaoeduardo.penabrancadelivery_backend.user.exception;

public class EmailAlreadyRegisteredException extends RuntimeException{
    public EmailAlreadyRegisteredException(String message){

        super(message);

    }
}
