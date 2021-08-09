package com.sample.postgress.bo;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import com.sample.postgress.HttpHandler.HttpsClient;
import com.sample.postgress.common.CommonMethods;
import com.sample.postgress.dao.CustomerDao;
import com.sample.postgress.entity.CommonResponse;
import com.sample.postgress.entity.Customer;
import com.sample.postgress.entity.MobileValidationResponse;
@Repository
public class CustomerBOImpl implements CustomerBO {

	@Resource 
	CustomerDao customerDao;
	
	@Override
	public MobileValidationResponse isMobileValid(Customer request) {
		MobileValidationResponse response = new MobileValidationResponse();
		
		//calling third party;
		HttpsClient httpClass = new HttpsClient();
		HashMap httpMap = httpClass.callapi(request.getMobileNumber());
		if (null != httpMap && Boolean.TRUE.equals(httpMap.get("valid"))) {
			response.setCountryCode(null != httpMap.get("country_name") ? httpMap.get("country_name").toString() : "" );
			response.setCountryName(null != httpMap.get("country_name") ? httpMap.get("country_name").toString() : "" );
			response.setOperatorName(null != httpMap.get("carrier") ? httpMap.get("carrier").toString() : "" );
			response.setOutputCode(0);
			response.setOutputDesc("valid Phone number");
			response.setValid(true);
		}
		else if (null != httpMap && Boolean.FALSE.equals(httpMap.get("valid")))
		{
			response.setOutputCode(-1);
			response.setOutputDesc("invalid phone number");
			response.setValid(false);
		}
		else {
			response.setOutputCode(-2);
			response.setOutputDesc("Connection Error");
			response.setValid(false);
		}

		
		return response;
	}

	@Override
	public List<Customer> findAll() {
		return customerDao.findAll();
	}

	@Override
	public CommonResponse insertCustomer(Customer c) {
		CommonResponse response = new CommonResponse();
		try {
			boolean isMobileValid = CommonMethods.isPhoneValid(c);
			if (isMobileValid) {
				int res = customerDao.insertCustomer(c);
				if (res == 1) {
					response.setOutputCode(0);
					response.setOutputDesc("customer is successfully created");
				}

				else {
					response.setOutputCode(-1);
					response.setOutputDesc("customer is not created");
				}			
			}

			else {
				response.setOutputCode(-1);
				response.setOutputDesc("mobile number is not valid");
				}
		}
		catch (Exception e) {
			response.setOutputCode(-1);
			response.setOutputDesc(e.getMessage());
		}
		
		
		return response;
	}

	@Override
	public CommonResponse updateCustomer(Customer c) {
		CommonResponse response = new CommonResponse();
		try {
			boolean isMobileValid = CommonMethods.isPhoneValid(c);
			if (isMobileValid) {
				int res = customerDao.updateCustomer(c);
				if (res == 1) {
					response.setOutputCode(0);
					response.setOutputDesc("customer info is successfully updated");
				}

				else {
					response.setOutputCode(-1);
					response.setOutputDesc("customer info was not updated");
				}
			}
			else {
				response.setOutputCode(-1);
				response.setOutputDesc("mobile number is not valid");
			}

		}
		catch (Exception e) {
			response.setOutputCode(-1);
			response.setOutputDesc(e.getMessage());
		}
		
		return response;
		
	}

	@Override
	public CommonResponse deleteCustomer(Customer c) {
		
		CommonResponse response = new CommonResponse();
		try {
			int res = customerDao.deleteCustomer(c);
			if (res == 1) {
				response.setOutputCode(0);
				response.setOutputDesc("customer is successfully deleted");
			}
			
			else {
				response.setOutputCode(-1);
				response.setOutputDesc("customer was not deleted");
			}			
		}
		catch (Exception e) {
			response.setOutputCode(-1);
			response.setOutputDesc(e.getMessage());
		}
		
		
		return response;
		
	}

}
