package com.oranth.applicationmarket.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * Swagger2 的配置文件
 * Created by qhs
 */
//@Configuration
public class SwaggerConfigure {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.oranth.applicationmarket.controller")) //指向你的controller包
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("应用商店系统")//这个会在Swagger页面中显示，
                .description("服务")
                .termsOfServiceUrl("http://192.168.199.153:10001")
                .version("1.0")
                .build();
    }
}