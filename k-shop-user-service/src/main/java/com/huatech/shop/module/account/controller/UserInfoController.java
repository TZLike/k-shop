package com.huatech.shop.module.account.controller;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.bean.WxMaUserInfo;
import com.huatech.shop.base.controller.BaseController;
import com.huatech.shop.common.constants.ApiConstants;
import com.huatech.shop.common.result.ResponseResult;
import com.huatech.shop.module.account.entity.UserInfo;
import com.huatech.shop.module.account.service.IUserService;
import com.huatech.shop.module.auth.entity.JwtUser;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @ClassName UserInfoController
 * @Description TODO
 * @Author like
 * @Date 2019-07-31 10:36
 * @Version 1.0
 **/
@RestController
@RequestMapping("/user")
@Slf4j
public class UserInfoController extends BaseController {


    @Autowired
    private WxMaService wxMaService;
    @Autowired
    private IUserService accountService;


    /**
     * @param code 小程序前端传递微信返回值code
     * @return
     */
    @RequestMapping(value = "/login/code", method = RequestMethod.GET)
    @ResponseBody
    public ResponseResult login(@RequestParam(value = "code", required = true) String code,
                                @RequestParam(value = "encryptedData", required = true) String encryptedData,
                                @RequestParam(value = "ivStr", required = true) String ivStr,
                                HttpServletRequest request) {
        if (StringUtils.isBlank(code)) {
            return new ResponseResult().error(ApiConstants.FAIL, "code不能为空");
        }
        try {
            //获取sessionKey
            WxMaJscode2SessionResult session = this.wxMaService.getUserService().getSessionInfo(code);
            if (session != null) {
                //获取微信里的用户信息
                WxMaUserInfo wxMaUserInfo = this.wxMaService.getUserService().getUserInfo(session.getSessionKey(), encryptedData, ivStr);
                if (wxMaUserInfo != null) {
                    UserInfo userInfo = accountService.createNewUser(wxMaUserInfo);
                    return new ResponseResult().ok(ApiConstants.SUCCESS, "success").addData("userInfo", userInfo);
                } else {
                    return new ResponseResult().error(ApiConstants.FAIL, "授权失败");
                }
            }

        } catch (WxErrorException e) {
            log.error("=================" + e.getMessage());
            return new ResponseResult().error(ApiConstants.FAIL, "授权失败");
        }

        return null;

    }


    //修改个人信息
    @RequestMapping(value = "/edit/info", method = RequestMethod.POST)
    public ResponseResult editUserInfo(@RequestBody UserInfo userInfo, HttpServletRequest httpServletRequest) {

        JwtUser info = getUserInfo(httpServletRequest);
        userInfo.setUserId(info.getUserId());
//        UserInfo changeUser = accountService.editUserInfo(userInfo);
        return new ResponseResult().ok(0, "修改用户信息成功").addData("userInfo", "userInfo");
    }


}
