package com.huatech.shop.module.auth.service.impl;

import com.huatech.shop.module.auth.entity.JwtUser;
import com.huatech.shop.module.auth.entity.JwtUtils;
import com.huatech.shop.module.auth.entity.Token;
import com.huatech.shop.module.auth.service.IJwtAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName JwtAuthServiceImpl
 * @Description TODO
 * @Author like
 * @Date 2019-07-31 13:18
 * @Version 1.0
 **/
@Service
public class JwtAuthServiceImpl implements IJwtAuthService {


    @Autowired
    private JwtUtils jwtUtils;

    @Override
    public Token createToken(Long userId, String openId, String unionId) {
        JwtUser jwtUser = JwtUser.builder().userId(userId).openId(openId).unionId(unionId).build();
        Token token = jwtUtils.createToken(jwtUser);
        return token;
    }


}
