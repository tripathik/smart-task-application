package com.example.smarttask.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI smartTaskOpenAPI(){
        return new OpenAPI().info(new Info().title("Smart Task API")
                .description("API documentation for Smart Task Application ")
                .version("v1.0")
                .license(new License().name("Apache 2.0").url("http://intelliguru.com")))
                .externalDocs(new ExternalDocumentation()
                        .description("Project Github Repository")
                        .url("https://github.com/tripathik/smart-task-application"));
    }
}
