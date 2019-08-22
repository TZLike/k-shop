package com.huatech.shop.module.auth.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName JwtUser
 * @Description TODO
 * @Author like
 * @Date 2019-07-31 13:33
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JwtUser {

    private Long userId;
    private String unionId; //小程序和微信公众号唯一标识
    private String openId;
}
