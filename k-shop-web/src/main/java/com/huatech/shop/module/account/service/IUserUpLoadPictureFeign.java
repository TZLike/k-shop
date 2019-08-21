package com.huatech.shop.module.account.service;

import com.huatech.shop.module.account.service.impl.UserUpLoadPictureFeignFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

/**
 * @ClassName IUserUpLoadPictureFeign
 * @Description TODO
 * @Author like
 * @Date 2019-08-21 13:58
 * @Version 1.0
 **/
@FeignClient(value = "k-shop-picture-server", fallback = UserUpLoadPictureFeignFallBack.class)
public interface IUserUpLoadPictureFeign {

    @RequestMapping(value = "/picture/upload", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    String uploadFileEditor(@RequestPart("file") MultipartFile file);
}
