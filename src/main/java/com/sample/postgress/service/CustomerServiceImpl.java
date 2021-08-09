package com.sample.postgress.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.sample.postgress.bo.CustomerBO;
import com.sample.postgress.common.CommonMethods;
import com.sample.postgress.dao.CustomerDao;
import com.sample.postgress.entity.CommonResponse;
import com.sample.postgress.entity.Customer;
import com.sample.postgress.entity.MobileValidationResponse;
@Component
public class CustomerServiceImpl implements CustomerService{
	@Resource 
	CustomerDao customerDao;
	@Resource
	CustomerBO customerBO;


	@Override
	public MobileValidationResponse isMobileValid(Customer request) {
		return customerBO.isMobileValid(request);
	}

	@Override
	public List<Customer> findAll() {
		return customerBO.findAll();
	}
	@Override
	public CommonResponse insertCustomer(Customer c) {
		return customerBO.insertCustomer(c);
	}
	@Override
	public CommonResponse updateCustomer(Customer c) {
		return customerBO.updateCustomer(c);
	}

	@Override
	public CommonResponse deleteCustomer(Customer c) {
		return customerBO.deleteCustomer(c);
	}

}
