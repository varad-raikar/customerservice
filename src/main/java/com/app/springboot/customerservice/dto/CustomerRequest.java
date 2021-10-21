package com.app.springboot.customerservice.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class CustomerRequest {

    @NotBlank(message = "first name is required")
    private String firstName;

    @NotBlank(message = "last name is required")
    private String lastName;

    @NotBlank(message = "email cannot be blank")
    private String email;

    @NotBlank(message = "contact no is required")
    private String contactNo;

    @NotBlank(message = "Address type is required")
    private String addressType;

    @NotBlank(message = "address cannot be blank")
    private String address;

    @NotBlank(message = "Subscription type can be SD/HD/FULL HD")
    private String subscriptionType;
}
