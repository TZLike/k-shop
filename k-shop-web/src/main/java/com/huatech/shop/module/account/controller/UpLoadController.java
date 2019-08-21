package com.huatech.shop.module.account.controller;

import com.huatech.shop.base.constants.ShopConstants;
import com.huatech.shop.base.controller.BaseController;
import com.huatech.shop.common.result.ResponseResult;
import com.huatech.shop.module.account.service.IUserUpLoadPictureFeign;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @ClassName UpLoadController
 * @Description TODO
 * @Author like  图片上传公共接口
 * @Date 2019-08-21 13:47
 * @Version 1.0
 **/
@RequestMapping(value = "/picture")
@RestController
@Slf4j
public class UpLoadController extends BaseController {

    @Autowired
    private IUserUpLoadPictureFeign userUpLoadPictureFeign;

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public ResponseResult upload(MultipartFile multipartFile) {

        String url = userUpLoadPictureFeign.uploadFileEditor(multipartFile);
        if (StringUtils.isNotBlank(url) && !url.equals("error")) {

            return new ResponseResult().ok(ShopConstants.SUCCESS, "success").addData("url", url);
        }
        return new ResponseResult().error(ShopConstants.FAIL, "fail");
    }
}
