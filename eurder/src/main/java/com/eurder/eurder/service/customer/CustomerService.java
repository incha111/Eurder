package com.eurder.eurder.service.customer;

import com.eurder.eurder.api.customer.dto.CreateCustomerDto;
import com.eurder.eurder.api.customer.dto.CustomerDto;
import com.eurder.eurder.domain.customer.Customer;
import com.eurder.eurder.domain.customer.CustomerRepository;
import com.eurder.eurder.service.security.Role;
import com.eurder.eurder.service.security.SecurityService;
import com.eurder.eurder.service.security.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    private final SecurityService securityService;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, CustomerMapper customerMapper, SecurityService securityService) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
        this.securityService = securityService;
    }

    public List<CustomerDto> getAllCustomers(){
        return customerMapper.toDto(customerRepository.getAllCustomers());
    }
    public CustomerDto getCustomerById(int customerId){
        return customerMapper.toDto(customerRepository.getCustomerById(customerId));
    }

    public CustomerDto createCustomer(CreateCustomerDto createCustomerDto){
        securityService.addUser(registerCustomer(createCustomerDto));
        return customerMapper.toDto(customerRepository.save(new Customer(
                        createCustomerDto.getFirstname(),
                        createCustomerDto.getLastname(),
                        createCustomerDto.getEmail(),
                        createCustomerDto.getPassword(),
                        createCustomerDto.getAddress(),
                        createCustomerDto.getPhone()
                )));
    }

    private User registerCustomer(CreateCustomerDto createCustomerDto){
        return new User(
                createCustomerDto.getFirstname(),
                createCustomerDto.getLastname(),
                createCustomerDto.getEmail(),
                createCustomerDto.getPassword(),
                Role.CUSTOMER);
    }
}
