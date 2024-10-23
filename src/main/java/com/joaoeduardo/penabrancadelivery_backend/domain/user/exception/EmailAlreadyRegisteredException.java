package com.joaoeduardo.penabrancadelivery_backend.domain.user.exception;

public class EmailAlreadyRegisteredException extends RuntimeException{
    public EmailAlreadyRegisteredException(String message){

        super(message);

    }
}
