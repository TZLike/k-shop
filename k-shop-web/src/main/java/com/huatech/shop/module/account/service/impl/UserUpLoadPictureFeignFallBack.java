package com.huatech.shop.module.account.service.impl;

import com.huatech.shop.module.account.service.IUserUpLoadPictureFeign;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

/**
 * @ClassName UserUpLoadPictureFeignFallBack
 * @Description TODO
 * @Author like
 * @Date 2019-08-21 13:59
 * @Version 1.0
 **/
@Component
public class UserUpLoadPictureFeignFallBack implements IUserUpLoadPictureFeign {
    
    @Override
    public String uploadFileEditor(MultipartFile file) {
        return "error";
    }
}
