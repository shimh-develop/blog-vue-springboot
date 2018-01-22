package com.shimh.common.controlleradvice;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.AuthorizationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.shimh.common.constant.ResultCode;
import com.shimh.common.exception.BaseException;
import com.shimh.common.util.Result;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
	@ExceptionHandler(AuthorizationException.class)
	ResponseEntity<Result> AuthorizationExceptionHandler(AuthorizationException e) {
	   
		logger.error(e.getMessage(),e);
		
		HttpStatus status = HttpStatus.UNAUTHORIZED;
	    
	    Result r = new Result();
	    r.setResultCode(ResultCode.PERMISSION_NO_ACCESS);
	    
	    return new ResponseEntity<Result>(r,status);
	}
	

	@ExceptionHandler(BaseException.class)
	ResponseEntity<Result> BaseExceptionHandler(HttpServletRequest request, BaseException e) {
		
		logger.error(e.getMessage(),e);
		
	    HttpStatus status = getStatus(request);
	    Result r = new Result();
	    r.setResultCode(ResultCode.SYSTEM_INNER_ERROR);
	    
	    r.simple().put("errdetail", e.getMessage());
	    
	    return new ResponseEntity<Result>(r,status);
	}
	
	private HttpStatus getStatus(HttpServletRequest request) {
	    Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
	    if (statusCode == null) {
	        return HttpStatus.INTERNAL_SERVER_ERROR;
	    }
	    return HttpStatus.valueOf(statusCode);
	}

}
