package com.clb.apis.v1;

import static com.clb.apis.dto.ApiResult.failed;

import java.util.NoSuchElementException;

import org.hibernate.PropertyValueException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.clb.apis.dto.ApiResult;

@RestControllerAdvice
public class GlobalControllerAdvice {

	@ExceptionHandler(NoSuchElementException.class)
	public ApiResult<?> noSuchElementExceptionHandler(NoSuchElementException e) {
		return failed("Invalid Identification Value");
	}
	
	@ExceptionHandler(EmptyResultDataAccessException.class)
	public ApiResult<?> emptyResultDataAccessExceptionHandler(EmptyResultDataAccessException e) {
		return failed("Invalid Target");
	}
	
	@ExceptionHandler(PropertyValueException.class)
	public ApiResult<?> propertyValueExceptionHandler(PropertyValueException e) {
		return failed("Invalid Resource");
	}
}
