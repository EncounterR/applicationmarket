package com.oranth.applicationmarket.exception.pojo;

public class LoginExceptionInfo extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String describe ;

	
	public LoginExceptionInfo(String describe) {
		super();
		this.describe = describe;
	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}
}
