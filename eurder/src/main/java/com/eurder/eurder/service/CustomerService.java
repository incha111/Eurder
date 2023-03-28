package com.eurder.eurder.service;

import com.eurder.eurder.api.CreateCustomerDto;
import com.eurder.eurder.api.CustomerDto;
import com.eurder.eurder.domain.Customer;
import com.eurder.eurder.domain.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public CustomerService(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    public List<CustomerDto> getAllCustomers(){
        return customerMapper.toDto(customerRepository.getAllCustomers());
    }

    public CustomerDto createCustomer(CreateCustomerDto createCustomerDto){
                Customer customer = new Customer(
                        createCustomerDto.getFirstname(),
                        createCustomerDto.getLastname(),
                        createCustomerDto.getEmail(),
                        createCustomerDto.getPassword(),
                        createCustomerDto.getAddress(),
                        createCustomerDto.getPhone()
                );
                return customerMapper.toDto(customerRepository.save(customer));
    }
}
