package com.sample.postgress.common;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import com.sample.postgress.HttpHandler.HttpsClient;
import com.sample.postgress.bo.CustomerBO;
import com.sample.postgress.dao.CustomerDao;
import com.sample.postgress.entity.CommonResponse;
import com.sample.postgress.entity.Customer;
import com.sample.postgress.entity.MobileValidationResponse;


public final class CommonMethods {

	public static boolean isPhoneValid(Customer request) {

		boolean response;
		try
		{
			MobileValidationResponse isMobileValid = new MobileValidationResponse();

			//calling third party;
			HttpsClient httpClass = new HttpsClient();
			HashMap httpMap = httpClass.callapi(request.getMobileNumber());
			if (null != httpMap && Boolean.TRUE.equals(httpMap.get("valid"))) {
				response = true;
			}
			else {
				response = false;
			}

		}
		catch (Exception e)
		{
			return false;
		}
		return response;
	}
}
