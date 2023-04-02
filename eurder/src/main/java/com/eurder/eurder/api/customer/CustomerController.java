package com.eurder.eurder.api.customer;

import com.eurder.eurder.api.customer.dto.CreateCustomerDto;
import com.eurder.eurder.api.customer.dto.CustomerDto;
import com.eurder.eurder.service.customer.CustomerService;
import com.eurder.eurder.service.security.Feature;
import com.eurder.eurder.service.security.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("customers")
public class CustomerController {
    private final CustomerService customerService;
    private final SecurityService securityService;

    @Autowired
    public CustomerController(CustomerService customerService, SecurityService securityService) {
        this.customerService = customerService;
        this.securityService = securityService;
    }

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public List<CustomerDto> getAllCustomers(@RequestHeader String authorization){
        securityService.validateAuthorization(authorization, Feature.VIEW_ALL_CUSTOMER);
        return customerService.getAllCustomers();
    }

    @GetMapping("{customerId}")
    @ResponseStatus(HttpStatus.OK)
    public CustomerDto getCustomerById(@PathVariable int customerId,@RequestHeader String authorization){
        securityService.validateAuthorization(authorization, Feature.VIEW_SINGLE_CUSTOMER);
        return customerService.getCustomerById(customerId);
    }
    @PostMapping(value = "register",produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerDto createCustomer( @RequestBody CreateCustomerDto createCustomerDto){
        return customerService.createCustomer(createCustomerDto);
    }
}
