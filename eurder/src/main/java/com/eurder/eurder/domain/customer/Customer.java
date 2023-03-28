package com.eurder.eurder.domain.customer;

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
}
