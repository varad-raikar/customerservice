package com.app.springboot.customerservice.service;

import com.app.springboot.customerservice.controller.CustomerAlreadyExistsException;
import com.app.springboot.customerservice.controller.CustomerNotFoundException;
import com.app.springboot.customerservice.controller.CustomerValidationException;
import com.app.springboot.customerservice.dto.AddAddressRequest;
import com.app.springboot.customerservice.dto.CustomerRequest;
import com.app.springboot.customerservice.dto.CustomerResponse;
import com.app.springboot.customerservice.dto.NumberUpdateRequest;
import com.app.springboot.customerservice.entity.Address;
import com.app.springboot.customerservice.entity.Customer;
import com.app.springboot.customerservice.entity.Subscription;
import com.app.springboot.customerservice.repo.AddressRepository;
import com.app.springboot.customerservice.repo.CustomerRepository;
import com.app.springboot.customerservice.repo.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ServiceImpl implements IService {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Override
    @Transactional
    public ResponseEntity<CustomerResponse> findCustomer(Integer id) {
        Customer customer = findById(id);
        CustomerResponse response = new CustomerResponse(customer);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    @Transactional
    public Customer findById(Integer id) {
        Optional<Customer> customerOptional = customerRepository.findById(id);
        if (customerOptional.isEmpty()){
            throw new CustomerNotFoundException("Customer id " + id + " not found");
        }

        return customerOptional.get();
    }

    @Override
    @Transactional
    public ResponseEntity<CustomerResponse> save(CustomerRequest inCustomer) {
        if (customerRepository.findByEmail(inCustomer.getEmail()) != null){
            throw new CustomerAlreadyExistsException("Customer already exists");
        }

        Customer customer = new Customer(inCustomer);

        Subscription subscription = subscriptionRepository.findByName((inCustomer.getSubscriptionType()).toUpperCase());
        if (subscription == null) {
            throw new CustomerValidationException("Invalid subscription type");
        }
        customer.setSubscription(subscription);

        Address address = new Address();
        address.setAddressType(inCustomer.getAddressType());
        address.setAddress(inCustomer.getAddress());

        List<Address> addressList = new ArrayList<Address>();
        addressList.add(address);
        customer.setAddress(addressList);
        customer = customerRepository.save(customer);

        CustomerResponse response = new CustomerResponse(customer);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Override
    @Transactional
    public ResponseEntity<CustomerResponse> updateNumber(NumberUpdateRequest inNumber) {
        Customer customer = findById(inNumber.getId());
        customer.setContactNo(inNumber.getNumber());
        customer = customerRepository.save(customer);

        CustomerResponse response = new CustomerResponse(customer);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<CustomerResponse> addAddress(AddAddressRequest inAddress) {
        Customer customer = findById(inAddress.getId());
        List<Address> addressList = customer.getAddress();

        if (addressList.size() > 0) {
            for (Address eachAddress : addressList) {
                if (eachAddress.getAddressType().equals(inAddress.getAddressType())) {
                    eachAddress.setAddress(inAddress.getAddress());
                    addressRepository.save(eachAddress);
                    CustomerResponse response = new CustomerResponse(customer);
                    return new ResponseEntity<>(response, HttpStatus.OK);
                }
            }

        }
        Address newAddress = new Address();
        newAddress.setAddressType(inAddress.getAddressType());
        newAddress.setAddress(inAddress.getAddress());
        addressList.add(newAddress);
        customer = customerRepository.save(customer);

        CustomerResponse response = new CustomerResponse(customer);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
