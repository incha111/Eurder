package com.eurder.eurder.domain.customer;

import com.eurder.eurder.service.security.SecurityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.format;

@Repository
public class CustomerRepository {
    private final Logger logger = LoggerFactory.getLogger(CustomerRepository.class);
    private final List<Customer> customerList;

    public CustomerRepository() {
        customerList = new ArrayList<>();
    }

    public List<Customer> getAllCustomers(){
        return customerList.stream()
                .collect(Collectors.toList());
    }
    public Customer getCustomerById(int customerId){
        return customerList.stream()
                .filter(c -> c.getId() == customerId)
                .findFirst()
                .orElseThrow(() -> {
                    logger.error(format("No Customer found for id " + customerId));
                    return new ResponseStatusException(HttpStatus.NOT_FOUND,"No Customer found for id " + customerId);
                });
    }

    public Customer save(Customer customer){
        if(!checkDuplicateInDatabase(customer)){
            customerList.add(customer);
        } else {
            logger.error(format("Customer already present in the database: " + customer.getId()));
            throw new ResponseStatusException(HttpStatus.FOUND,"This customer is already present in the database.");
        }
        return customer;
    }

    private boolean checkDuplicateInDatabase(Customer customer){
        return customerList.stream()
                .filter(c -> c.getFirstname().equals(customer.getFirstname()))
                .filter(c -> c.getLastname().equals(customer.getLastname()))
                .anyMatch(c -> c.getAddress().equals(customer.getAddress()));
    }
}
