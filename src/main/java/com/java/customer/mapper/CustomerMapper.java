package com.java.customer.mapper;

import java.time.LocalDate;

import com.java.customer.dto.CustomerDto;
import com.java.customer.entity.Customer;

public class CustomerMapper {

	public static CustomerDto mapToCustomerDto(Customer customer) {
		return new CustomerDto(customer.getId(), customer.getFirstName(), customer.getLastName(), customer.getEmail(),
				customer.getDob().toString(), customer.getAge());
	}

	public static Customer mapToCustomer(CustomerDto customerDto) {
		LocalDate dob = LocalDate.parse(customerDto.getDob());
		return new Customer(customerDto.getId(), customerDto.getFirstName(), customerDto.getLastName(),
				customerDto.getEmail(), dob, customerDto.getAge());
	}

}
