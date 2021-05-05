package com.stc.demo.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerConfig {


    private static final String DEFAULT_PRODUCES_AND_CONSUMES = new String("application/json");
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .produces(Collections.singleton(DEFAULT_PRODUCES_AND_CONSUMES))
                .consumes(Collections.singleton(DEFAULT_PRODUCES_AND_CONSUMES));
    }
}