package com.huatech.shop.base.controller;

import com.alibaba.fastjson.JSON;
import com.huatech.shop.module.auth.entity.JwtUser;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author leek
 * @Date 2018-10-25 上午9:58
 * @Version 1.0
 * @Description
 */
public class BaseController {
    @Autowired
    protected HttpServletResponse response;


    //获取用户userId
    public JwtUser getUserInfo(HttpServletRequest request) {

        //从请求header中获取token
        String token = (String) request.getAttribute("userInfo");
        JwtUser jwtUser = JSON.parseObject(token, JwtUser.class);
        return jwtUser;

    }


    //从header中获取用户的token
    public String getToken(HttpServletRequest request) {
        //从请求header中获取token
        String token = request.getHeader("authorization");
        if (StringUtils.isNotBlank(token)) {
            return token;
        }
        return null;
    }
}
