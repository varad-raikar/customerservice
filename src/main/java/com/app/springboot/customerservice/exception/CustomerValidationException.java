package com.app.springboot.customerservice.exception;

public class CustomerValidationException extends RuntimeException{
    public CustomerValidationException(String message) {
        super(message);
    }
}
