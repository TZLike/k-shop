package com.huatech.shop.module.account.mapper;


import com.huatech.shop.module.account.entity.UserInfo;
import tk.mybatis.mapper.common.BaseMapper;

public interface UserInfoMapper extends BaseMapper<UserInfo> {

    //根据用户openId来查询用户是否已经存在
    UserInfo findUserInfoByOpenId(String openId);
}