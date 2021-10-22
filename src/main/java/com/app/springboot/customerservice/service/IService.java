package com.app.springboot.customerservice.service;

import com.app.springboot.customerservice.dto.AddAddressRequest;
import com.app.springboot.customerservice.dto.CustomerRequest;
import com.app.springboot.customerservice.dto.CustomerResponse;
import com.app.springboot.customerservice.dto.NumberUpdateRequest;

public interface IService {
//    public Customer findById(int theId);
    public CustomerResponse save(CustomerRequest inCustomer);
    public CustomerResponse updateNumber(NumberUpdateRequest inNumber);
    public CustomerResponse addAddress(AddAddressRequest inAddress);
}
