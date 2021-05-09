package com.clb.apis.v1;

import static com.clb.apis.dto.ApiResult.failed;

import java.util.NoSuchElementException;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.clb.apis.dto.ApiResult;

@RestControllerAdvice
public class GlobalControllerAdvice {

	@ExceptionHandler(NoSuchElementException.class)
	public ApiResult<?> noSuchElementExceptionHandler() {
		return failed("This id doesn't exist!");
	}
	
	@ExceptionHandler(EmptyResultDataAccessException.class)
	public ApiResult<?> emptyResultDataAccessExceptionHandler() {
		return failed("This id doesn't exist!");
	}
}
