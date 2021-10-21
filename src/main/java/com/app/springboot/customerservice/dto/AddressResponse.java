package com.app.springboot.customerservice.dto;

import com.app.springboot.customerservice.entity.Address;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressResponse {

    private String addressType;

    private String address;

    public AddressResponse(Address theAddress){
        this.addressType = theAddress.getAddressType();
        this.address = theAddress.getAddress();
    }
}
