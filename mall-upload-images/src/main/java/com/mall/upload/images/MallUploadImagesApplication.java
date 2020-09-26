package com.mall.upload.images;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MallUploadImagesApplication {

    public static void main(String[] args) {
        SpringApplication.run(MallUploadImagesApplication.class, args);
    }

}
