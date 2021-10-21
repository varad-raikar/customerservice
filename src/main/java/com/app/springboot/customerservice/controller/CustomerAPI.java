package com.app.springboot.customerservice.controller;

import com.app.springboot.customerservice.dto.CustomerRequest;
import com.app.springboot.customerservice.dto.CustomerResponse;
import com.app.springboot.customerservice.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/api/customer/")
public class CustomerAPI {

    @Autowired
    IService customerService;

    @PostMapping("create")
    public CustomerResponse save(@Valid @RequestBody CustomerRequest inCustomer){
        return customerService.save(inCustomer);
    }

}
