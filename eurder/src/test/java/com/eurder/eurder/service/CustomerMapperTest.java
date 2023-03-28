package com.eurder.eurder.service;

import com.eurder.eurder.api.customer.dto.CustomerDto;
import com.eurder.eurder.domain.customer.Customer;
import com.eurder.eurder.service.customer.CustomerMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class CustomerMapperTest {
    CustomerMapper customerMapper;

    @BeforeEach
    void setUp() {
        customerMapper = new CustomerMapper();
    }

    @Test
    void toDto_givingACustomer_thenReturnsACustomerDto() {
        //given
        Customer customer = new Customer("Louis","Koppens","louis.Koppens@gmail.com","123","BeCentral 2","123456");

        //when
        CustomerDto customerDto = customerMapper.toDto(customer);

        //then
        org.assertj.core.api.Assertions.assertThat(customerDto).isEqualTo(customerMapper.toDto(customer));
    }

    @Test
    void toDto_givingAListOfCustomers_thenReturnsAListOfCustomerDto() {
        //given
        Customer customer1 = new Customer("Louis","Koppens","louis.Koppens@gmail.com","123","BeCentral 2","123456");
        Customer customer2 = new Customer("Lisa","Martens","lisa.martens@gmail.com","123","BeCentral 1","654321");
        List<Customer> customerList = new ArrayList<>();
        customerList.add(customer1);
        customerList.add(customer2);

        //when
        List<CustomerDto> customerDtoList = customerMapper.toDto(customerList);

        //then
        org.assertj.core.api.Assertions.assertThat(customerDtoList).isEqualTo(customerMapper.toDto(customerList));
    }
}