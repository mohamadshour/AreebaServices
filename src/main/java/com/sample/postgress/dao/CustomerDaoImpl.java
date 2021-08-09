package com.sample.postgress.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.sample.postgress.entity.Customer;
import com.sample.postgress.mapper.CustomerRowMapper;
@Repository
public class CustomerDaoImpl implements CustomerDao{
	
	public CustomerDaoImpl(NamedParameterJdbcTemplate template) {  
        this.template = template;  
}  
	NamedParameterJdbcTemplate template;  

	@Override
	public List<Customer> findAll() {
		return template.query("SELECT * FROM AR_CUSTOMER", new CustomerRowMapper());
	}
	@Override
	public int insertCustomer(Customer c) {
		 final String sql = "INSERT INTO AR_CUSTOMER(NAME,MOBILE_NUMBER, ADDRESS) values(:name,:mobileNumber,:address)";
		 
	        KeyHolder holder = new GeneratedKeyHolder();
	        SqlParameterSource param = new MapSqlParameterSource()
					.addValue("name", c.getName())
					.addValue("mobileNumber", c.getMobileNumber())
					.addValue("address", c.getAddress());
	        return template.update(sql,param, holder);
	 
	}
	
	@Override
	public int updateCustomer(Customer c) {
		 final String sql = "UPDATE AR_CUSTOMER SET NAME=:name, ADDRESS=:address, MOBILE_NUMBER=:mobileNumber WHERE CUSTOMER_ID=:customerId";
		 
	        KeyHolder holder = new GeneratedKeyHolder();
	        SqlParameterSource param = new MapSqlParameterSource()
	        		.addValue("name", c.getName())
	        		.addValue("address", c.getAddress())
	        		.addValue("mobileNumber", c.getMobileNumber())
	        		.addValue("customerId", c.getCustomerId());
	       return template.update(sql,param, holder);
	 
	}
	
	@Override
	public int deleteCustomer(Customer c) {
		 final String sql = "DELETE FROM AR_CUSTOMER WHERE CUSTOMER_ID=:customerId";
		 int resp = 0;
		 Map<String,Object> map=new HashMap<String,Object>();  
		 map.put("customerId", c.getCustomerId());
	
		 template.execute(sql,map,new PreparedStatementCallback<Object>() {  
			 int response = 0;
				@Override  
			    public Object doInPreparedStatement(PreparedStatement ps)  
			            throws SQLException, DataAccessException {  
			    	return response = ps.executeUpdate();
			    
			    }  
			});
		return 1;

	 
	}
	
}
