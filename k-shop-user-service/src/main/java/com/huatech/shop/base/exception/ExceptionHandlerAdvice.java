package com.huatech.shop.base.exception;

import com.huatech.shop.common.exception.ExceptionCustomer;
import com.huatech.shop.common.result.ResponseResult;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author leek
 * @Date 2018-10-23 上午11:07
 * @Version 1.0
 * @Description
 */
@ControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseResult handlerException(Exception e) {

        if (e instanceof ExceptionCustomer) {
            ExceptionCustomer exceptionCustomer = (ExceptionCustomer) e;
            return new ResponseResult().error(exceptionCustomer.getErrorCode(),exceptionCustomer.getMessage());
        } else if (e instanceof MissingServletRequestParameterException) {
            MissingServletRequestParameterException exception = (MissingServletRequestParameterException) e;
            String parameterName = exception.getParameterName();
            return new ResponseResult().error(-1,"参数缺失"+parameterName);

        } else if (e instanceof HttpMessageNotReadableException) {
            return new ResponseResult().error(-1,"请传递完整参数");
        } else {
            e.printStackTrace();
            return new ResponseResult().error(-1,"服务器开小差~请稍后再试...");
        }
    }


}