package com.example.swaggerdemo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Predicate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.nio.file.Paths;
import java.util.ArrayList;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket docket(Environment environment){
        //监听项目的环境
        boolean flag = environment.acceptsProfiles(Profiles.of("dev", "test"));

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .enable(flag)
                .select()// 通过.select()方法，去配置扫描接口,RequestHandlerSelectors配置如何扫描接口
                // 通过方法上的注解扫描，如withMethodAnnotation(GetMapping.class)只扫描get请求
                // 通过类上的注解扫描，如withClassAnnotation(Controller.class)只扫描有controller注解的类中的接口
                .apis(RequestHandlerSelectors.basePackage("com.example.swaggerdemo.controller"))
                //  指定路径下的请求
                // .paths(PathSelectors.ant("/api/**"))
                .build();
    }
    public ApiInfo apiInfo(){
        Contact contact = new Contact("chao", "", "1394949540@qq.com");
        return new ApiInfo(
                "朱继超的SwaggerAPI接口文档"
                , "Hello"
                , "v1.0"
                , "urn:tos"
                , contact
                , "Apache 2.0"
                , "http://www.apache.org/licenses/LICENSE-2.0"
                , new ArrayList());
    }
}
