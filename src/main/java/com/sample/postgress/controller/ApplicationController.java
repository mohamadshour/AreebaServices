package com.sample.postgress.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sample.postgress.entity.CommonResponse;
import com.sample.postgress.entity.Customer;
import com.sample.postgress.service.CustomerService;
@CrossOrigin
@RestController
@RequestMapping("/areebaServices")
public class ApplicationController {

	@Resource 
	CustomerService customerService;
	
	@PostMapping(value = "/validateMobile")
	public CommonResponse validateMobile(@RequestBody Customer request) {
		return customerService.isMobileValid(request);
	
	}
	
	@GetMapping(value = "/customerList")
	public CommonResponse getCustomers() {
		CommonResponse response = new CommonResponse();
		List<Customer> result = customerService.findAll();
		if (result.size() > 0) {
			response.setOutputCode(0);
			response.setOutputDesc("Sucess");
			response.setList(result);
		}
		else
		{
			response.setOutputCode(-405);
			response.setOutputDesc("No data found");
		}
		return response;
	}
	
	@PostMapping(value = "/createCust")
	public CommonResponse createCustomer(@RequestBody Customer emp) {
		 return customerService.insertCustomer(emp);
	
	}
	@PutMapping(value = "/updateCust")
	public CommonResponse updateCustomer(@RequestBody Customer emp) {
		return customerService.updateCustomer(emp);
	
	}
	
	@DeleteMapping(value = "/deleteCustById")
	public CommonResponse deleteCustomer(@RequestBody Customer emp) {
		return customerService.deleteCustomer(emp);
	
	}
	
	
}
