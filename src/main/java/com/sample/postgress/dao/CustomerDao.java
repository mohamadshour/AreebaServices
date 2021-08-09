package com.sample.postgress.dao;

import java.util.List;

import com.sample.postgress.entity.Customer;

public interface CustomerDao {

	List <Customer> findAll();

	int insertCustomer(Customer cust);

	int updateCustomer(Customer cust);

	public int deleteCustomer(Customer cust);
}
