package com.adoo.album.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI albumOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Album API")
                        .description("Modulo de usuarios, notificaciones y reportes.)")
                        .version("1.0.0")
                );
    }
}
