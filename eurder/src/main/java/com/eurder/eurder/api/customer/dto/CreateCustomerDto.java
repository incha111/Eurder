package com.eurder.eurder.api.customer.dto;

public class CreateCustomerDto {
    private final String firstname;
    private final String lastname;
    private final String email;
    private final String password;
    public final String address;
    public final String phone;
    public CreateCustomerDto(String firstname, String lastname, String email,  String address, String phone,String password) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.address = address;
        this.phone = phone;
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
