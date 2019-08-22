package com.huatech.shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@tk.mybatis.spring.annotation.MapperScan("com.huatech.shop.module.**.mapper")
public class KShopUserServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(KShopUserServiceApplication.class, args);
    }

}
