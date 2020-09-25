package com.mall.admin.order.apply;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
@MapperScan(basePackages = "com.mall.admin.order.apply.dao")
public class MallAdminOrderReturnApplyApplication {

	public static void main(String[] args) {
		SpringApplication.run(MallAdminOrderReturnApplyApplication.class, args);
	}

}
