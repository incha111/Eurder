package com.eurder.eurder.service;

import com.eurder.eurder.api.customer.dto.CustomerDto;
import com.eurder.eurder.domain.customer.Customer;
import com.eurder.eurder.domain.customer.CustomerRepository;
import com.eurder.eurder.service.customer.CustomerMapper;
import com.eurder.eurder.service.customer.CustomerService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

class CustomerServiceTest {
    CustomerRepository customerRepositoryMock;
    CustomerMapper customerMapperMock;
    CustomerService customerService;

    @BeforeEach
    void setUpCustomerServiceTest() {
        customerRepositoryMock = Mockito.mock(CustomerRepository.class);
        customerMapperMock = Mockito.mock(CustomerMapper.class);
        customerService = new CustomerService(customerRepositoryMock,customerMapperMock);
    }

    @Test
    void getAllCustomers_verifyMethodGetAllCustomerIsCalledOnCustomerService() {
        //given
        //when
        customerService.getAllCustomers();

        //then
        Mockito.verify(customerRepositoryMock).getAllCustomers();
    }

    @Test
    void getAllCustomers_givenAListOfCustomer_returnAListOfCustomerDto() {
        //given
        Customer customer1 = new Customer("Louis","Koppens","louis.Koppens@gmail.com","123","BeCentral 2","123456");
        Customer customer2 = new Customer("Lisa","Martens","lisa.martens@gmail.com","123","BeCentral 1","654321");
        List<Customer> customerList = new ArrayList<>();
        customerList.add(customer1);
        customerList.add(customer2);
        CustomerMapper customerMapper = new CustomerMapper();
        Mockito.when(customerService.getAllCustomers())
                .thenReturn(customerMapper.toDto(customerList));
        //when
        List<CustomerDto> actual = customerService.getAllCustomers();

        //then
        Assertions.assertThat(actual).isEqualTo(customerService.getAllCustomers());
    }

//    @Test
//    void save_verifySaveMethodIsCalledOnCustomerService() {
//        //given
//        CustomerMapper customerMapper = new CustomerMapper();
//        CreateCustomerDto createcustomerDto = new CreateCustomerDto("Louis","Koppens","louis.Koppens@gmail.com","123","BeCentral 2","123456");
//        Customer customer = new Customer("Louis","Koppens","louis.Koppens@gmail.com","123","BeCentral 2","123456");
//        Mockito.when(customerService.createCustomer(createcustomerDto))
//                .thenReturn(customerMapper.toDto(customer));
//
//        //when
//        customerService.createCustomer(createcustomerDto);
//
//        //then
//        Mockito.verify(customerRepositoryMock).save(customer);
//    }
}