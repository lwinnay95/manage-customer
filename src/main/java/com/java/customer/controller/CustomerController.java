package com.java.customer.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.java.customer.dto.CustomerDto;
import com.java.customer.service.CustomerService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/customer")
public class CustomerController {

	private final CustomerService customerService;

	@PostMapping
	public ResponseEntity<CustomerDto> createCustomer(@Valid @RequestBody CustomerDto customerDto) {
		CustomerDto savedCustomer = customerService.createCustomer(customerDto);
		return new ResponseEntity<>(savedCustomer, HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<CustomerDto> getCustomerById(@PathVariable(name = "id") Long customerId) {
		CustomerDto customerDto = customerService.getCustomerById(customerId);
		return ResponseEntity.ok(customerDto);
	}

	@GetMapping
	public ResponseEntity<List<CustomerDto>> getAllCustomers() {
		List<CustomerDto> customers = customerService.getAllCustomers();
		return ResponseEntity.ok(customers);
	}

	@PutMapping("/{id}")
	public ResponseEntity<CustomerDto> updateCustomer(@PathVariable(name = "id") Long customerId,
			@Valid @RequestBody CustomerDto customerDto) {
		CustomerDto updatedCustomer = customerService.updateCustomer(customerId, customerDto);
		return ResponseEntity.ok(updatedCustomer);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteCustomer(@PathVariable(name = "id") Long customerId) {
		customerService.deleteCustomer(customerId);
		return ResponseEntity.ok("Customer is deleted successfully!");
	}

}
