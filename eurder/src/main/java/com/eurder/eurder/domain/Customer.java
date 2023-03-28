package com.eurder.eurder.domain;

public class Customer extends User{
    public final String address;
    public final String phone;
    public Customer(String firstname, String lastname, String email, String password, String address, String phone) {
        super(firstname, lastname, email, password);
        this.address = address;
        this.phone = phone;
        this.role = Role.CUSTOMER;
    }
}
