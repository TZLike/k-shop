package com.huatech.shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class KShopEurekaServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(KShopEurekaServerApplication.class, args);
    }

}
