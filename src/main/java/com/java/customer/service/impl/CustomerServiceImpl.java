package com.java.customer.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.java.customer.dto.CustomerDto;
import com.java.customer.entity.Customer;
import com.java.customer.exception.DuplicateResourceException;
import com.java.customer.exception.ResourceNotFoundException;
import com.java.customer.mapper.CustomerMapper;
import com.java.customer.repository.CustomerRepository;
import com.java.customer.service.CustomerService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {

	private final CustomerRepository customerRepository;

	@Override
	public CustomerDto createCustomer(CustomerDto customerDto) {
		Optional<Customer> customerOptional = customerRepository.findCustomerByEmail(customerDto.getEmail());

		if (customerOptional.isPresent()) {
			throw new DuplicateResourceException("Email is taken");
		}

		Customer savedCustomer = customerRepository.save(CustomerMapper.mapToCustomer(customerDto));
		return CustomerMapper.mapToCustomerDto(savedCustomer);
	}

	@Override
	public CustomerDto getCustomerById(Long customerId) {
		Customer customer = customerRepository.findById(customerId).orElseThrow(
				() -> new ResourceNotFoundException("Customer does not exist with given id : " + customerId));
		return CustomerMapper.mapToCustomerDto(customer);
	}

	@Override
	public List<CustomerDto> getAllCustomers() {
		List<Customer> customers = customerRepository.findAll();
		return customers.stream().map((customer) -> CustomerMapper.mapToCustomerDto(customer))
				.collect(Collectors.toList());
	}

	@Override
	public CustomerDto updateCustomer(Long customerId, CustomerDto customerDto) {
		Customer customer = customerRepository.findById(customerId).orElseThrow(
				() -> new ResourceNotFoundException("Customer does not exist with given id : " + customerId));

		customer.setFirstName(customerDto.getFirstName());
		customer.setLastName(customerDto.getLastName());

		if (!Objects.equals(customer.getEmail(), customerDto.getEmail())) {
			Optional<Customer> customerOptional = customerRepository.findCustomerByEmail(customerDto.getEmail());

			if (customerOptional.isPresent()) {
				throw new DuplicateResourceException("Email is taken");
			}

			customer.setEmail(customerDto.getEmail());
		}

		customer.setDob(LocalDate.parse(customerDto.getDob()));

		Customer updatedCustomer = customerRepository.save(customer);

		return CustomerMapper.mapToCustomerDto(updatedCustomer);
	}

	@Override
	public void deleteCustomer(Long customerId) {
		customerRepository.findById(customerId).orElseThrow(
				() -> new ResourceNotFoundException("Customer does not exist with given id : " + customerId));

		customerRepository.deleteById(customerId);
	}

}
