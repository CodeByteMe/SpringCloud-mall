package com.mall.order.add;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
@EnableEurekaClient
@MapperScan(basePackages = "com.mall.order.add.dao")
public class MallOrderAddApplication {

    public static void main(String[] args) {
        SpringApplication.run(MallOrderAddApplication.class, args);
    }

}
