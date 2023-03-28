package com.eurder.eurder.service;

import com.eurder.eurder.api.dto.CustomerDto;
import com.eurder.eurder.domain.Customer;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CustomerMapper {
    public CustomerDto toDto(Customer customer){
        return new CustomerDto(
                customer.getId(),
                customer.getFirstname(),
                customer.getLastname(),
                customer.getEmail(),
                customer.getAddress(),
                customer.getPhone(),
                customer.getPassword()
        );
    }

    public List<CustomerDto> toDto(List<Customer> customerList){
        return customerList.stream()
                .map(u -> toDto(u))
                .collect(Collectors.toList());
    }
}
