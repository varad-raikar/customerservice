package com.app.springboot.customerservice.service;

import com.app.springboot.customerservice.dto.AddAddressRequest;
import com.app.springboot.customerservice.dto.CustomerRequest;
import com.app.springboot.customerservice.dto.CustomerResponse;
import com.app.springboot.customerservice.dto.NumberUpdateRequest;
import com.app.springboot.customerservice.entity.Customer;
import org.springframework.http.ResponseEntity;

public interface IService {
    ResponseEntity<CustomerResponse> findCustomer(Integer id);
    Customer findById(Integer id);
    ResponseEntity<CustomerResponse> save(CustomerRequest inCustomer);
    ResponseEntity<CustomerResponse> updateNumber(NumberUpdateRequest inNumber);
    ResponseEntity<CustomerResponse> addAddress(AddAddressRequest inAddress);
}
