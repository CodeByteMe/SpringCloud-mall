package com.mall.admin.product.add;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
@MapperScan(basePackages = "com.mall.admin.product.add.dao")
public class MallAdminProductAddApplication {

    public static void main(String[] args) {
        SpringApplication.run(MallAdminProductAddApplication.class, args);
    }

}
