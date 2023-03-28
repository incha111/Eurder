package com.eurder.eurder.domain.customer;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class CustomerRepository {
    private final List<Customer> customerList;

    public CustomerRepository() {
        customerList = new ArrayList<>();
        customerList.add(new Customer("Lisa","Koppens","Lisa.Koppens@gmail.com","123","my address", "my phone"));
    }

    public List<Customer> getAllCustomers(){
        return customerList.stream()
                .collect(Collectors.toList());
    }

    public Customer save(Customer customer){
        customerList.add(customer);
        return customer;
    }
}
