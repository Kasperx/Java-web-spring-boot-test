package com.example.demo.exceptionHandler;

import org.hibernate.exception.JDBCConnectionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;


@ControllerAdvice
public class GlobalExceptionHandler {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	@ExceptionHandler(NotFoundException.class)
	public ModelAndView HandleNotFoundException(Exception ex) {
		LOGGER.error(ex.getMessage(), ex);
	    
		ModelAndView mav = new ModelAndView();
	    mav.setViewName("not_found");
	    mav.addObject("message", ex.getLocalizedMessage());
	    return mav;
	}

	@ExceptionHandler(JDBCConnectionException.class)
	public ModelAndView handleDBConnectionError() {
		ModelAndView mav = new ModelAndView("connection_error");
		return mav;
	}
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ModelAndView handleDataIntegrityViolationException() {
		ModelAndView mav = new ModelAndView("div_error");
		return mav;
		
	}
	
}
