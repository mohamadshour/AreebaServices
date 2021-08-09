package com.sample.postgress.entity;

import java.util.List;

public class CommonResponse {

	private int outputCode;
	private String outputDesc;
	List<Customer> list;
	
	
	public List<Customer> getList() {
		return list;
	}
	public void setList(List<Customer> list) {
		this.list = list;
	}
	public int getOutputCode() {
		return outputCode;
	}
	public void setOutputCode(int outputCode) {
		this.outputCode = outputCode;
	}
	public String getOutputDesc() {
		return outputDesc;
	}
	public void setOutputDesc(String outputDesc) {
		this.outputDesc = outputDesc;
	}
	
}
