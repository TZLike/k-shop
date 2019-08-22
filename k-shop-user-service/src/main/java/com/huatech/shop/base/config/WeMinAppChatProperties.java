package com.huatech.shop.base.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Author leek
 * @Date 2018-08-11 下午5:35
 * @Version 1.0
 * @Description 微信小程序配置文件
 */
@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WeMinAppChatProperties {
    /**
     * 设置微信小程序的appid
     */
    @Value("${wechat.miniapp.appid}")
    private String appid;

    /**
     * 设置微信小程序的Secret
     */
    @Value("${wechat.miniapp.secret}")
    private String secret;

    /**
     * 设置微信小程序的token
     */
    @Value("${wechat.miniapp.token}")
    private String token;

    /**
     * 设置微信小程序的EncodingAESKey
     */
    @Value("${wechat.miniapp.aesKey}")
    private String aesKey;

    /**
     * 消息格式，XML或者JSON
     */
    @Value("${wechat.miniapp.msgDataFormat}")
    private String msgDataFormat;


}
