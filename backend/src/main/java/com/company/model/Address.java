package com.company.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "addresses")
@SuppressWarnings("serial")
public class Address implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = true)
    private String state;

    @Column(nullable = true)
    private String city;

    @Column(nullable = true)
    private String street;

    @Column(nullable = true)
    private String number;

    public Address(String state, String city, String street, String number) {
        this.state = state;
        this.city = city;
        this.street = street;
        this.number = number;
    }
}
