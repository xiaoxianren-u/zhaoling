package com.edu.config;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author yz
 * @data: 2021/10/5 18:22 星期二
 * @file : MySwaggerConfig.java
 */

@Configuration
@EnableWebMvc
@EnableSwagger2
public class MySwaggerConfig {

    @Bean
    public Docket myDocket() {
        Docket docket = new Docket(DocumentationType.SWAGGER_2);
        ApiInfo apiInfo = new ApiInfoBuilder()
                // 标题
                .title("招领管理系统[Api接口文档]")
                // 描述
                .description("")
                // 联系方式
                .contact(new Contact("yang_", "", "1793925141@qq.com"))
                // 版本号
                .version("v1.0")
                .build();
        docket.apiInfo(apiInfo);
        //设置只生成被Api这个注解注解过的Ctrl类中有ApiOperation注解的api接口的文档
        docket.select().apis(RequestHandlerSelectors.withClassAnnotation(Api.class)).apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class)).build();
        return docket;
    }

}
