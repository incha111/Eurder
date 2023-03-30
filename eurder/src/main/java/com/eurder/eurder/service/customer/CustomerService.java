package com.eurder.eurder.service.customer;

import com.eurder.eurder.api.customer.dto.CreateCustomerDto;
import com.eurder.eurder.api.customer.dto.CustomerDto;
import com.eurder.eurder.domain.customer.Customer;
import com.eurder.eurder.domain.customer.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    public List<CustomerDto> getAllCustomers(){
        return customerMapper.toDto(customerRepository.getAllCustomers());
    }
    public CustomerDto getCustomerById(int customerId){
        return customerMapper.toDto(customerRepository.getCustomerById(customerId));
    }

    public CustomerDto createCustomer(CreateCustomerDto createCustomerDto){
        return customerMapper.toDto(customerRepository.save(new Customer(
                        createCustomerDto.getFirstname(),
                        createCustomerDto.getLastname(),
                        createCustomerDto.getEmail(),
                        createCustomerDto.getPassword(),
                        createCustomerDto.getAddress(),
                        createCustomerDto.getPhone()
                )));
    }
}
