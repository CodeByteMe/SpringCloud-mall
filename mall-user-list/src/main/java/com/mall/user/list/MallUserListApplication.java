package com.mall.user.list;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
@MapperScan(basePackages = "com.mall.user.list.dao")
public class MallUserListApplication {

    public static void main(String[] args) {
        SpringApplication.run(MallUserListApplication.class, args);
    }

}
