package com.mall.common.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * SwaggerConfig
 *
 * @Author BessCroft
 * @Date 2020/9/20 20:03
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket getDocket(){
        //Docket对象用于设置接口说明信息
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(getApiInfo())   //设置说明文档的“封面”信息
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.mall"))  //指定扫描接口的范围
                .paths(PathSelectors.any())
                .build();
        return docket;
    }

    public ApiInfo getApiInfo(){
        ApiInfoBuilder builder = new ApiInfoBuilder()
                .title("巢庭万家接口说明文档")          //设置标题
                .description("这是api接口说明文档的描述信息")
                .version("1.0.1")
                .contact(new Contact("mall","http://www.springcloud-mall.com","haha@hehe.com"));

        ApiInfo apiInfo = builder.build();
        return apiInfo;
    }

}
