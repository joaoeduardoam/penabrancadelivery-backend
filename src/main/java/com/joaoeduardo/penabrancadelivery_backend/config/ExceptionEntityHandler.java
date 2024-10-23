package com.joaoeduardo.penabrancadelivery_backend.config;

import br.com.efi.efisdk.exceptions.EfiPayException;
import com.joaoeduardo.penabrancadelivery_backend.domain.product.exception.ProductNotFoundException;
import com.joaoeduardo.penabrancadelivery_backend.security.exception.AuthenticationException;
import com.joaoeduardo.penabrancadelivery_backend.domain.user.exception.EmailAlreadyRegisteredException;
import com.joaoeduardo.penabrancadelivery_backend.domain.user.exception.UserNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.Instant;
import java.time.format.DateTimeParseException;

@ControllerAdvice
public class ExceptionEntityHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<StandardError> handleUserNotFoundException(UserNotFoundException exception, HttpServletRequest request){
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError error = new StandardError(Instant.now(), status.value(), "USER not found!",
                exception.getMessage(), request.getRequestURI());

        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<StandardError> handleProductNotFoundException(ProductNotFoundException exception, HttpServletRequest request){
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError error = new StandardError(Instant.now(), status.value(), "Product not found!",
                exception.getMessage(), request.getRequestURI());

        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(EmailAlreadyRegisteredException.class)
    public ResponseEntity<StandardError> handleEmailAlreadyRegisteredException(EmailAlreadyRegisteredException exception, HttpServletRequest request){
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError error = new StandardError(Instant.now(), status.value(), "The provided email is already registered!!",
                exception.getMessage(), request.getRequestURI());

        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<StandardError> handleAuthenticationException(AuthenticationException exception, HttpServletRequest request){

        HttpStatus status = HttpStatus.UNAUTHORIZED;
        StandardError error = new StandardError(Instant.now(), status.value(), exception.getMessage() ,
                exception.getMessage(), request.getRequestURI());

        return ResponseEntity.status(status).body(error);
    }


    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<StandardError> handleValidationException(ValidationException exception, HttpServletRequest request){

        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError error = new StandardError(Instant.now(), status.value(), "Validation Error!",
                exception.getMessage(), request.getRequestURI());

        return ResponseEntity.status(status).body(error);

    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<StandardError> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException exception,
                                                                            HttpServletRequest request){

        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError error = new StandardError(Instant.now(), status.value(), "Resource Not Found with provided ID!",
                exception.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(DateTimeParseException.class)
    public ResponseEntity<StandardError> handleDateTimeParseException(DateTimeParseException exception,
                                                                                   HttpServletRequest request){

        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError error = new StandardError(Instant.now(), status.value(), "Provided date is invalid!",
                exception.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(EfiPayException.class)
    public ResponseEntity<StandardError> handleEfiPayException(EfiPayException exception,
                                                                      HttpServletRequest request){

        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError error = new StandardError(Instant.now(), status.value(), exception.getError(),
                exception.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(error);
    }



}
