package com.huatech.shop.base.config;

import com.github.tobato.fastdfs.FdfsClientConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.context.annotation.Import;
import org.springframework.jmx.support.RegistrationPolicy;

/**
 * @Author leek
 * @Date 2018-08-09 下午4:39
 * @Version 1.0
 * @Description
 */
@Configuration
@Import(FdfsClientConfig.class)//注解，就可以拥有带有连接池的FastDFS Java客户端了
@EnableMBeanExport(registration = RegistrationPolicy.IGNORE_EXISTING)
public class FdfsConfiguration {
}
