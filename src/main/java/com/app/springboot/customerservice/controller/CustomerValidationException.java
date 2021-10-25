package com.app.springboot.customerservice.controller;

public class CustomerValidationException extends RuntimeException{
    public CustomerValidationException(String message) {
        super(message);
    }
}
