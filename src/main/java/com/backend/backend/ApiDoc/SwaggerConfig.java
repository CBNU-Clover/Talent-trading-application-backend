package com.backend.backend.ApiDoc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
//접속법 http://localhost:8080/swagger-ui.html
public class SwaggerConfig {

    private ApiInfo LoginapiInfo() {
        return new ApiInfoBuilder()
                .title("Talent-trading-application-API")
                .description("API")
                .build();
    }

    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(this.LoginapiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.backend.backend.controller"))
                .paths(PathSelectors.any())
                .build();
    }
}
