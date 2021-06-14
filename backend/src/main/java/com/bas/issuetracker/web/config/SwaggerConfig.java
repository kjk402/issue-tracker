package com.bas.issuetracker.web.config;

import com.bas.issuetracker.web.config.annotation.CertifiedUser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@EnableSwagger2
@Configuration
public class SwaggerConfig {

    @Bean
    public Docket api() {
        ApiInfo apiInfo = new ApiInfoBuilder()
                .title("Issue-Tracker API")
                .description("Spring API made by Bat and Nas")
                .license("Apache License Version 2.0")
                .version("0.1")
                .build();

        Set<String> responseContentType = new HashSet<>();
        responseContentType.add("application/json;charset=UTF-8");

        List global = new ArrayList();
        global.add(new ParameterBuilder().name("Authorization").
                defaultValue("bearer ").
                description("Access Token").parameterType("header").
                required(false).modelRef(new ModelRef("string")).build());

        // http://localhost:8080/swagger-ui.html
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("issueTracker")
                .produces(responseContentType)
                .apiInfo(apiInfo)
                .globalOperationParameters(global)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.bas.issuetracker")) // NOTE: basic-error-controller 는 문서화 하지 않음
                .paths(PathSelectors.ant("/**"))
                .build().ignoredParameterTypes(CertifiedUser.class);
    }

}
