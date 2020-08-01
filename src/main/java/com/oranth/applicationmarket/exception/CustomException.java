package com.oranth.applicationmarket.exception;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.ResponseStatus;
//import org.springframework.web.servlet.ModelAndView;
//
//@ControllerAdvice
//public class CustomException {
//	/**
//	 * 运行时异常
//	 * 
//	 * @param exception
//	 * @return
//	 */
//	@ExceptionHandler({ RuntimeException.class })
//	@ResponseStatus(HttpStatus.OK)
//	public ModelAndView processException(RuntimeException exception) {
//		System.out.println("发生异常跳转到错误页面1");
//		ModelAndView mv=new ModelAndView("404");
//		return mv;
//	}
//
//	/**
//	 * Excepiton异常
//	 * 
//	 * @param exception
//	 * @return
//	 */
//	@ExceptionHandler({ Exception.class })
//	@ResponseStatus(HttpStatus.OK)
//	public ModelAndView processException(Exception exception) {
//		System.out.println("发生异常跳转到错误页面2");
//		ModelAndView mv=new ModelAndView("404");
//		return mv;
//	}
//}
