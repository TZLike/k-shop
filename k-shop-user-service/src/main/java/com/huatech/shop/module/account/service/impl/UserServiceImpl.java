package com.huatech.shop.module.account.service.impl;

import cn.binarywang.wx.miniapp.bean.WxMaUserInfo;
import com.google.common.collect.Maps;
import com.huatech.shop.module.account.entity.UserInfo;
import com.huatech.shop.module.account.mapper.UserInfoMapper;
import com.huatech.shop.module.account.service.IUserService;
import com.huatech.shop.module.auth.entity.Token;
import com.huatech.shop.module.auth.service.IJwtAuthService;
import com.huatech.shop.module.cache.ICacheService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Map;

/**
 * @ClassName UserServiceImpl
 * @Description TODO
 * @Author like
 * @Date 2019-07-31 10:42
 * @Version 1.0
 **/
@Service
public class UserServiceImpl implements IUserService {


    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private IJwtAuthService jwtAuthService;


    @Value("${redis.token.prefix}")
    private String tokenPrefix;

    @Value("${jwt.expiration}")
    private Long expiration;

    @Value("${hualiao.token}")
    private String token_str;

    @Value("${hualiao.common.url}")
    private String common_url;

    private static final String UNION_ID_URL_GET = "/front/anon/openUser/getUserByUnionId.json";


    @Autowired
    private ICacheService cacheService;


    @Override
    public UserInfo createNewUser(WxMaUserInfo wxMaUserInfo) {

        //通过openId来检查用户是否已经存在
        UserInfo info = findUserInfoByOpenId(wxMaUserInfo.getOpenId());

        if (info == null) {
            //如果为空，说明还没有注册用户，则新注册一个用户
            //add leek 2019-08-20  保存用户unionId,作为唯一小程序和公众号的唯一标识
            UserInfo user = registerUser(wxMaUserInfo);
            //生成token　
            Token token = jwtAuthService.createToken(user.getUserId(), user.getOpenid(), wxMaUserInfo.getUnionId());
            user.setToken(token);
            //保存token到redis里
            cacheService.set(tokenPrefix + user.getUserId(), token.getAccessToken(), expiration);
            return user;

        }

        Token token = jwtAuthService.createToken(info.getUserId(), info.getOpenid(), wxMaUserInfo.getUnionId());
        info.setToken(token);
        //保存token到redis里
        cacheService.set(tokenPrefix + info.getUserId(), token.getAccessToken(), expiration);
        return info;
    }


    @Override
    public UserInfo findUserInfoByOpenId(String openId) {
        return userInfoMapper.findUserInfoByOpenId(openId);
    }


    public UserInfo registerUser(WxMaUserInfo wxMaUserInfo) {

        UserInfo userInfo = new UserInfo();
        userInfo.setCreateTime(new Date());
        userInfo.setWxName(wxMaUserInfo.getNickName());
        userInfo.setOpenid(wxMaUserInfo.getOpenId());
        userInfo.setUnionId(wxMaUserInfo.getUnionId());
        userInfo.setSex(wxMaUserInfo.getGender());
        userInfo.setProfileImgUrl(wxMaUserInfo.getAvatarUrl());
        if (StringUtils.isNotBlank(wxMaUserInfo.getUnionId())) {   //add leek  2019-08-20
            userInfo.setUnionId(wxMaUserInfo.getUnionId());
        }
        userInfoMapper.insertSelective(userInfo);
        return userInfo;
    }
}
