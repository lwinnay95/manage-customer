package com.java.customer.service;

import java.util.List;

import com.java.customer.dto.CustomerDto;

public interface CustomerService {

	public CustomerDto createCustomer(CustomerDto customerDto);

	public CustomerDto getCustomerById(Long customerId);

	public List<CustomerDto> getAllCustomers();

	public CustomerDto updateCustomer(Long customerId, CustomerDto customerDto);

	public void deleteCustomer(Long customerId);

}
