package com.bhavesh16281.accounts.mapper;

import com.bhavesh16281.accounts.dto.CustomerDTO;
import com.bhavesh16281.accounts.dto.CustomerDetailsDto;
import com.bhavesh16281.accounts.entity.Customer;

public class CustomerMapper {

    public static CustomerDTO mapToCustomerDto(Customer customer, CustomerDTO customerDto) {

        customerDto.setName(customer.getName());
        customerDto.setEmail(customer.getEmail());
        customerDto.setPhone(customer.getPhone());

        return customerDto;

    }

    public static Customer mapToCustomer(CustomerDTO customerDto, Customer customer) {

        customer.setName(customerDto.getName());
        customer.setEmail(customerDto.getEmail());
        customer.setPhone(customerDto.getPhone());

        return customer;
    }

    public static CustomerDetailsDto mapToCustomerDetailsDto(Customer customer,CustomerDetailsDto customerDetailsDto) {

        customerDetailsDto.setName(customer.getName());
        customerDetailsDto.setEmail(customer.getEmail());
        customerDetailsDto.setPhone(customer.getPhone());

        return customerDetailsDto;
    }
}
