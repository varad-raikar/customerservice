package com.app.springboot.customerservice.dto;

import com.app.springboot.customerservice.entity.Address;
import com.app.springboot.customerservice.entity.Customer;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class CustomerResponse {

    private int customerId;

    private String firstName;

    private String lastName;

    private String email;

    private String contactNo;

    private List<AddressResponse> addressResponses;

    private String subscriptionType;

    private boolean isError = false;

    public CustomerResponse(Customer theCustomer){
        this.customerId = theCustomer.getId();
        this.firstName = theCustomer.getFirstName();
        this.lastName = theCustomer.getLastName();
        this.email = theCustomer.getEmail();
        this.contactNo = theCustomer.getContactNo();

        if(theCustomer.getSubscription() != null){
            this.subscriptionType = theCustomer.getSubscription().getName();
        }

        if(theCustomer.getAddress().size() > 0) {
            this.addressResponses = new ArrayList<AddressResponse>();
            for (Address theAddress : theCustomer.getAddress()) {
                this.addressResponses.add(new AddressResponse(theAddress));
            }
        }
    }
}
