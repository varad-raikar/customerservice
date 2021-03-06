package com.app.springboot.customerservice.entity;

import com.app.springboot.customerservice.dto.CustomerRequest;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@Data
@Table(name="customer_details")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "contact_no")
    private String contactNo;

    @OneToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "subscription_id")
    private Subscription subscription;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id")
    private List<Address> address;

    public Customer(CustomerRequest theCustomer){
        this.firstName = theCustomer.getFirstName();
        this.lastName = theCustomer.getLastName();
        this.email = theCustomer.getEmail();
        this.contactNo = theCustomer.getContactNo();
    }
}
