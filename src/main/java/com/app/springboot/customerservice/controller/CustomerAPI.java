package com.app.springboot.customerservice.controller;

import com.app.springboot.customerservice.dto.AddAddressRequest;
import com.app.springboot.customerservice.dto.CustomerRequest;
import com.app.springboot.customerservice.dto.CustomerResponse;
import com.app.springboot.customerservice.dto.NumberUpdateRequest;
import com.app.springboot.customerservice.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/customer/")
public class CustomerAPI {

    @Autowired
    IService customerService;

    @PostMapping("create")
    public CustomerResponse save(@Valid @RequestBody CustomerRequest inCustomer){
        return customerService.save(inCustomer);
    }

    @PutMapping("updNumber")
    public CustomerResponse updateNumber(@Valid @RequestBody NumberUpdateRequest inNumber){
        return customerService.updateNumber(inNumber);
    }

    @PostMapping("addAddress")
    public CustomerResponse addAddress(@Valid @RequestBody AddAddressRequest newAddress){
        return customerService.addAddress(newAddress);
    }

}
