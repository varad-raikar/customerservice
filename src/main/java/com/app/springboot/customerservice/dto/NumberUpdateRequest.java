package com.app.springboot.customerservice.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class NumberUpdateRequest {
    @NotBlank(message = "Customer id cannot be blank")
    private int id;

    @NotBlank(message = "Please provide the phone number to be updated")
    private String number;
}
