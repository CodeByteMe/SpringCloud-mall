package com.mall.address.list;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
@MapperScan(basePackages = "com.mall.address.list.dao")
public class MallAddressListApplication {

    public static void main(String[] args) {
        SpringApplication.run(MallAddressListApplication.class, args);
    }

}
