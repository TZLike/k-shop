package com.huatech.shop.base.constants;

/**
 * @ClassName ErrorCode
 * @Description TODO
 * @Author like
 * @Date 2019-08-17 17:02
 * @Version 1.0
 **/
public enum ErrorCode {
    ROLE_NAME_EXIST(10000, "角色名已经存在"),
    RESOURCE_NOT_EXIST(20000, "资源数据不存在"),
    RESOURCE_HAVE_CHILD(20001, "此资源是子资源不可一次删除"),
    DICT_IS_EXISTS(30000, "数据字典已经存在"),
    DICT_INFO_IS_EXISTS(30001, "数据明细已经存在"),
    BANNER_NOT_EXISTS(40000, "banner不存在"),
    ;


    ErrorCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    private Integer code;
    private String message;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
