package com.huatech.shop.common.exception;


/**
 * @Author leek
 * @Date 2018-06-14 下午5:43
 * @Version 1.0
 * @Description
 */
public class ExceptionCustomer extends RuntimeException {

    //异常信息
    private String message;
    //异常编码
    private Integer code = -1;

    private Integer errorCode;


    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }


    public ExceptionCustomer(String message) {
        super(message);
        this.message = message;

    }

    public ExceptionCustomer(Integer errorCode, String message) {
        super(message);
        this.message = message;
        this.errorCode = errorCode;
    }


    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }
}
