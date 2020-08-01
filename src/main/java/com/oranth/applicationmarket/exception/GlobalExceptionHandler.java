package com.oranth.applicationmarket.exception;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oranth.applicationmarket.exception.pojo.ExceptionInfo;
import com.oranth.applicationmarket.exception.pojo.LoginExceptionInfo;
import org.springframework.web.bind.annotation.RestController;


@ControllerAdvice
@RestController
public class GlobalExceptionHandler {
	/**
	 * 全局错误返回
	 * @param e
	 * @return
	 */
	@ExceptionHandler(ExceptionInfo.class)
	public String exceptionHandler(ExceptionInfo e) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", e.getCode());
		map.put("message", e.getMessage());
		map.put("method", e.getMethod());
		map.put("descinfo", e.getDescinfo());
		return JSON.toJSONString(map);
	}
	/**
	 * 
	 * @param e
	 * @return
	 */
	@ResponseBody
	@ExceptionHandler(LoginExceptionInfo.class)
	public String loginExceptionHandler(LoginExceptionInfo e){
		return e.getDescribe();
	}
}
