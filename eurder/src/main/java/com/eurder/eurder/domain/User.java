package com.eurder.eurder.domain;

public abstract class User {
    public int id;
    public static int counter = 0;
    private final String firstname;
    private final String lastname;
    private final String email;
    protected Role role;
    private final String password;

    public User(String firstname, String lastname, String email, String password) {
        this.id = ++counter;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
    }
}
