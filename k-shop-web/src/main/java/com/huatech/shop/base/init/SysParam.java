package com.huatech.shop.base.init;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author leek
 * @Date 2018-07-02 下午5:10
 * @Version 1.0
 * @Description
 */
@Configuration
public class SysParam {

    @Bean(initMethod = "initMethod")
    public SysParamService sysParamService() {
        SysParamService sysParamService = new SysParamService();
        return sysParamService;
    }

}
