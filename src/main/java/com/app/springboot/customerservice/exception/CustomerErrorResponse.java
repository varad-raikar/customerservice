package com.app.springboot.customerservice.exception;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
public class CustomerErrorResponse {

    private int status;
    private boolean isError = true;
}
