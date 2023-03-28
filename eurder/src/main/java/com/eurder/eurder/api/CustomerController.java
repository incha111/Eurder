package com.eurder.eurder.api;

import com.eurder.eurder.api.dto.CreateCustomerDto;
import com.eurder.eurder.api.dto.CustomerDto;
import com.eurder.eurder.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("customers")
public class CustomerController {
    private final CustomerService customerService;

    @Autowired
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
