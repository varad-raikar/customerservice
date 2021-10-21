package com.app.springboot.customerservice.service;

import com.app.springboot.customerservice.dto.CustomerRequest;
import com.app.springboot.customerservice.dto.CustomerResponse;
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

    @Override
    @Transactional
    public Customer findById(int theId) {
        return null;
    }

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
//        address = addressRepository.save(address);

        List<Address> addressList = new ArrayList<Address>();
        addressList.add(address);
        customer.setAddress(addressList);
        customer = customerRepository.save(customer);

        CustomerResponse response = new CustomerResponse(customer);
        return response;
    }
}
