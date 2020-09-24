package com.mall.admin.order.list;

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
@MapperScan(basePackages = "com.mall.admin.order.list.dao")
public class MallAdminOrderListApplication {

	public static void main(String[] args) {
		SpringApplication.run(MallAdminOrderListApplication.class, args);
	}

}
