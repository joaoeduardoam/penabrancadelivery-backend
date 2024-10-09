package com.joaoeduardo.penabrancadelivery_backend.security.exception;

import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;

public class AuthenticationException extends RuntimeException {

    public AuthenticationException(String message) {

        super(message);

    }

    public AuthenticationException(String message, JWTCreationException jwtCreationException) {

        super(message, jwtCreationException);
    }

    public AuthenticationException(String message, JWTVerificationException jwtVerificationException) {

        super(message, jwtVerificationException);
    }

}
