package com.app.springboot.customerservice.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Data
@Table(name = "address_details")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "address_type")
    private String addressType;

    @Column(name = "address")
    private String address;

//    @ManyToOne
//    @JoinColumn(name = "customer_id")
//    private Customer customer;

}
