package com.eurder.eurder.domain.customer;

import jakarta.persistence.*;

import java.util.Objects;

//@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "customer")
public class Customer {
   // public static int counter = 0;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_id_seq")
    @SequenceGenerator(sequenceName = "customer_id_seq", allocationSize = 1, name = "customer_id_seq")
    @Column(name = "id")
    public int id;
    @Column(name = "firstname")
    private String firstname;
    @Column(name = "lastname")
    private String lastname;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "address")
    protected String address;
    @Column(name = "phone")
    protected String phone;

    public Customer() {
    }

    public Customer(String firstname, String lastname, String email, String password, String address, String phone) {
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
