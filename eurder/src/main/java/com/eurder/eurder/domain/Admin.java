package com.eurder.eurder.domain;

public class Admin extends User{
    public Admin(String firstname, String lastname, String email, String password) {
        super(firstname, lastname, email, password);
        this.role = Role.ADMIN;
    }
}
