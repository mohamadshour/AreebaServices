package com.sample.postgress.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.sample.postgress.entity.Customer;

public class CustomerRowMapper implements RowMapper<Customer> {

	@Override
	public Customer mapRow(ResultSet rs, int arg1) throws SQLException {
		Customer c = new Customer();
		c.setCustomerId(rs.getInt("CUSTOMER_ID"));
		c.setName(rs.getString("NAME"));
		c.setMobileNumber(rs.getString("MOBILE_NUMBER"));
		c.setAddress(rs.getString("ADDRESS"));
 
        return c;
	}


}
