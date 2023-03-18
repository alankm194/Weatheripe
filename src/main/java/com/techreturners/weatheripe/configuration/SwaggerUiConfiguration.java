package com.techreturners.weatheripe.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerUiConfiguration {

    @Bean
    public OpenAPI customOpenAPI(@Value("${application-title}") String appTitle, @Value("${application-description}") String appDescription, @Value("${application-version}") String appVersion) {
        return new OpenAPI()
                .info(new Info()
                        .title(appTitle)
                        .version(appVersion)
                        .description(appDescription));
    }
}
