package com.mall.admin.order.reason;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
@MapperScan(basePackages = "com.mall.admin.home.dao")
public class MallAdminOrderReturnReasonApplication {

	public static void main(String[] args) {
		SpringApplication.run(MallAdminOrderReturnReasonApplication.class, args);
	}

}
