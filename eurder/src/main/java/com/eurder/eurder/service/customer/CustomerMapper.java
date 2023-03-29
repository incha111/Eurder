package com.eurder.eurder.service.customer;

import com.eurder.eurder.api.customer.dto.CustomerDto;
import com.eurder.eurder.domain.customer.Customer;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CustomerMapper {
    public CustomerDto toDto(Customer customer){
        return new CustomerDto(
                customer.getCustomerId(),
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
