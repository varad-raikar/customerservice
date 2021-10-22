package com.app.springboot.customerservice.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class AddAddressRequest {

    @NotBlank(message = "Customer id is required")
    private int id;

    @NotBlank(message = "Address type is required")
    private String addressType;

    @NotBlank(message = "address cannot be blank")
    private String address;
}
