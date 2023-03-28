package com.eurder.eurder.api;

import com.eurder.eurder.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("customers")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public List<CustomerDto> getAllCustomers(){
        return customerService.getAllCustomers();
    }

    @PostMapping(value = "register",produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerDto createCustomer( @RequestBody CreateCustomerDto createCustomerDto){
        return customerService.createCustomer(createCustomerDto);
    }
}
