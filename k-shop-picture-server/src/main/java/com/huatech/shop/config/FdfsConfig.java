package com.huatech.shop.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @Author leek
 * @Date 2018-08-09 下午4:38
 * @Version 1.0
 * @Description
 */
@Configuration
@Data
public class FdfsConfig {

    @Value("${fdfs.resHost}")
    private String resHost;

    @Value("${fdfs.storagePort}")
    private String storagePort;


}
