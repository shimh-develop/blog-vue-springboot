package com.shimh.common.controlleradvice;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shimh.common.exception.BaseException;

@ControllerAdvice
public class GlobalExceptionHandler {
	

	@ExceptionHandler(BaseException.class)
	@ResponseBody
	ResponseEntity<?> handleControllerException(HttpServletRequest request, Throwable ex) {
	    HttpStatus status = getStatus(request);
	    //return new ResponseEntity<>(new CustomErrorType(status.value(), ex.getMessage()), status);
	    
	    return new ResponseEntity<>(status);
	}
	
	private HttpStatus getStatus(HttpServletRequest request) {
	    Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
	    if (statusCode == null) {
	        return HttpStatus.INTERNAL_SERVER_ERROR;
	    }
	    return HttpStatus.valueOf(statusCode);
	}

}
