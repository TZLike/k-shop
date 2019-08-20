package com.huatech.shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@tk.mybatis.spring.annotation.MapperScan("com.huatech.shop.module.**.mapper")
public class KShopWebApplication {

    public static void main(String[] args) {


        SpringApplication.run(KShopWebApplication.class, args);
    }

}
