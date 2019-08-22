package com.huatech.shop.module.auth.service;


import com.huatech.shop.module.auth.entity.Token;

/**
 * @ClassName IJwtAuthService
 * @Description TODO
 * @Author like
 * @Date 2019-07-31 13:16
 * @Version 1.0
 **/
public interface IJwtAuthService {

    Token createToken(Long userId, String openId, String unionId);
}
