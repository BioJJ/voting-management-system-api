package com.biojj.app.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    @Bean
    OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Voting Management System Api")
                        .description("Documentação da API voting-management-system-api")
                        .version("v0.0.1"))
                .externalDocs(new ExternalDocumentation()
                        .url("https://github.com/BioJJ"));
    }
}
