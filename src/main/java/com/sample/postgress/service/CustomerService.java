package com.sample.postgress.service;

import java.util.List;

import com.sample.postgress.entity.CommonResponse;
import com.sample.postgress.entity.Customer;
import com.sample.postgress.entity.MobileValidationResponse;

public interface CustomerService {
	
	MobileValidationResponse isMobileValid(Customer request);
	
	List<Customer> findAll();

	CommonResponse insertCustomer(Customer cust);

	CommonResponse updateCustomer(Customer cust);

	CommonResponse deleteCustomer(Customer cust);
	
}
