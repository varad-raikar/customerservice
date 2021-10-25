package com.app.springboot.customerservice.controller;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CustomerErrorResponse {

    private int status;
    private boolean isError = true;
}
