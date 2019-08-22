package com.huatech.shop.module.account.service;

import cn.binarywang.wx.miniapp.bean.WxMaUserInfo;
import com.huatech.shop.module.account.entity.UserInfo;

/**
 * @ClassName IUserService
 * @Description TODO
 * @Author like
 * @Date 2019-07-31 10:42
 * @Version 1.0
 **/
public interface IUserService {

    UserInfo createNewUser(WxMaUserInfo wxMaUserInfo);

    UserInfo findUserInfoByOpenId(String openId);


}
