package com.mall.admin.product.sku;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
@MapperScan(basePackages = "com.mall.admin.product.sku.dao")
public class MallAdminProductSkuApplication {

    public static void main(String[] args) {
        SpringApplication.run(MallAdminProductSkuApplication.class, args);
    }

}
