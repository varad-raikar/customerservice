package com.app.springboot.customerservice.service;

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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServiceImpl implements IService {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private SubscriptionRepository subscriptionRepository;

//    @Override
//    @Transactional
//    public Customer findById(int theId) {
//        return findById(theId);
//    }

    @Override
    @Transactional
    public CustomerResponse save(CustomerRequest inCustomer) {
        if (customerRepository.findByEmail(inCustomer.getEmail()) != null){
            throw new RuntimeException("Customer already exists");
        }

        Customer customer = new Customer(inCustomer);

        Subscription subscription = subscriptionRepository.findByName((inCustomer.getSubscriptionType()).toUpperCase());
        if (subscription == null) {
            throw new RuntimeException("Invalid subscription type");
        }
        customer.setSubscription(subscription);

        Address address = new Address();
        address.setAddressType(inCustomer.getAddressType());
        address.setAddress(inCustomer.getAddress());

        List<Address> addressList = new ArrayList<Address>();
        addressList.add(address);
        customer.setAddress(addressList);
        customer = customerRepository.save(customer);

        return new CustomerResponse(customer);
    }

    @Override
    @Transactional
    public CustomerResponse updateNumber(NumberUpdateRequest inNumber) {
        Customer customer = customerRepository.findById(inNumber.getId()).get();
        if (customer == null) {
            throw new RuntimeException("Customer not found");
        }
        customer.setContactNo(inNumber.getNumber());
        customer = customerRepository.save(customer);
        return new CustomerResponse(customer);
    }

    @Override
    @Transactional
    public CustomerResponse addAddress(AddAddressRequest inAddress) {
        Customer customer = customerRepository.findById(inAddress.getId()).get();
        if (customer == null) {
            throw new RuntimeException("Customer not found");
        }
        List<Address> addressList = customer.getAddress();

        if (addressList.size() > 0) {
            for (Address eachAddress : addressList) {
                if (eachAddress.getAddressType().equals(inAddress.getAddressType())) {
                    eachAddress.setAddress(inAddress.getAddress());
                    addressRepository.save(eachAddress);
                    return new CustomerResponse(customer);
                }
            }

        }
        Address newAddress = new Address();
        newAddress.setAddressType(inAddress.getAddressType());
        newAddress.setAddress(inAddress.getAddress());
        addressList.add(newAddress);
        customer = customerRepository.save(customer);
        return new CustomerResponse(customer);
    }
}
