package com.huatech.shop.upload;

import com.huatech.shop.config.FastDFSClientWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @ClassName UploadController
 * @Description TODO
 * @Author like
 * @Date 2019-08-21 13:42
 * @Version 1.0
 **/
@RequestMapping(value = "/picture")
@RestController
@Slf4j
public class UploadController {

    @Autowired
    private FastDFSClientWrapper clientWrapper;

    /**
     * 上传图片
     *
     * @param multipartFile
     * @return
     */
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String bannerUpload(MultipartFile multipartFile) {
        String fileUrl = "";
        if (multipartFile != null) {
            try {
                fileUrl = clientWrapper.uploadFile(multipartFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return fileUrl;
    }
}
