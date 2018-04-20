package com.shimh.common.controlleradvice;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.shimh.common.constant.ResultCode;
import com.shimh.common.exception.BaseException;
import com.shimh.common.result.ParameterInvalidItem;
import com.shimh.common.result.Result;

/**
 * 全局异常处理器
 *
 * @author shimh
 * <p>
 * 2018年1月23日
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result handleMethodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest request) {

        logger.error("参数校验错误 , uri: {} , caused by: ", request.getRequestURI(), e);

        List<ParameterInvalidItem> parameterInvalidItemList = new ArrayList<>();

        List<FieldError> fieldErrorList = e.getBindingResult().getFieldErrors();
        for (FieldError fieldError : fieldErrorList) {
            ParameterInvalidItem parameterInvalidItem = new ParameterInvalidItem();
            parameterInvalidItem.setFieldName(fieldError.getField());
            parameterInvalidItem.setMessage(fieldError.getDefaultMessage());
            parameterInvalidItemList.add(parameterInvalidItem);
        }

        return Result.error(ResultCode.PARAM_IS_INVALID, parameterInvalidItemList);
    }


    @ExceptionHandler(AuthorizationException.class)
    ResponseEntity<Result> AuthorizationExceptionHandler(HttpServletRequest request, AuthorizationException e) {

        logger.error("权限认证错误 , uri: {} , caused by: ", request.getRequestURI(), e);

        HttpStatus status = HttpStatus.UNAUTHORIZED;

        Result r = new Result();
        r.setResultCode(ResultCode.PERMISSION_NO_ACCESS);

        return new ResponseEntity<Result>(r, status);
    }

    @ExceptionHandler(UnauthenticatedException.class)
    ResponseEntity<Result> UnauthenticatedExceptionHandler(HttpServletRequest request, UnauthenticatedException e) {

        logger.error("登录认证错误 , uri: {} , caused by: ", request.getRequestURI(), e);

        HttpStatus status = HttpStatus.OK;

        Result r = new Result();
        r.setResultCode(ResultCode.USER_NOT_LOGGED_IN);

        return new ResponseEntity<Result>(r, status);
    }


    @ExceptionHandler(Exception.class)
    ResponseEntity<Result> ExceptionHandler(HttpServletRequest request, Exception e) {

        logger.error("服务器内部错误 , uri: {} , caused by: ", request.getRequestURI(), e);

        HttpStatus status = getStatus(request);
        Result r = new Result();
        r.setResultCode(ResultCode.SYSTEM_INNER_ERROR);

        r.simple().put("errdetail", e.getMessage());

        return new ResponseEntity<Result>(r, status);
    }

    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.valueOf(statusCode);
    }

}
