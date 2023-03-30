package com.eurder.eurder.domain.customer;

import java.util.Objects;

//@JsonIgnoreProperties(ignoreUnknown = true)
public class Customer {
    public static int counter = 0;
    public final int id;
    private final String firstname;
    private final String lastname;
    private final String email;
    private final String password;
    protected final String address;
    protected final String phone;

    public Customer(String firstname, String lastname, String email, String password, String address, String phone) {
        this.id = ++counter;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.address = address;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return id == customer.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
