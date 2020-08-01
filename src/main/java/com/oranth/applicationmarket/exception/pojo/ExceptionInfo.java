package com.oranth.applicationmarket.exception.pojo;

public class ExceptionInfo extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String code;  //异常状态码

    private String message;  //异常信息

    private String method;   //发生的方法，位置等

    private Object descinfo;   //描述

	public ExceptionInfo(String code, String message, String method,
			Object descinfo) {
		super();
		this.code = code;
		this.message = message;
		this.method = method;
		this.descinfo = descinfo;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public Object getDescinfo() {
		return descinfo;
	}

	public void setDescinfo(Object descinfo) {
		this.descinfo = descinfo;
	}
	
}
