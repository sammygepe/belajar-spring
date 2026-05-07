package com.sammy.belajar_spring.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Components;

import io.swagger.v3.oas.models.info.Info;

import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 📘 Swagger OpenAPI Configuration
 */
@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {

        // Nama security scheme
        final String securitySchemeName =
                "bearerAuth";

        return new OpenAPI()

                // ==========================================
                // INFO API
                // ==========================================
                .info(
                        new Info()
                                .title("Belajar Spring Boot API")
                                .version("1.0")
                                .description(
                                        "REST API belajar Spring Boot + JWT + RBAC"
                                )
                )

                // ==========================================
                // JWT GLOBAL SECURITY
                // ==========================================
                .addSecurityItem(
                        new SecurityRequirement()
                                .addList(securitySchemeName)
                )

                // ==========================================
                // SECURITY SCHEME
                // ==========================================
                .components(
                        new Components()
                                .addSecuritySchemes(
                                        securitySchemeName,

                                        new SecurityScheme()
                                                .name("Authorization")

                                                // type auth = HTTP
                                                .type(SecurityScheme.Type.HTTP)

                                                // pakai bearer token
                                                .scheme("bearer")

                                                // format token JWT
                                                .bearerFormat("JWT")
                                )
                );
    }
}