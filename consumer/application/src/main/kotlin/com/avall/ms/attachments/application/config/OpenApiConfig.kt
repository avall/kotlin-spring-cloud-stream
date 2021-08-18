package com.avall.ms.attachments.application.config

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.security.SecurityRequirement
import io.swagger.v3.oas.models.servers.Server
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class OpenApiConfig {
    @Value("\${openapi-server-url:https://localhost:8006/crm-attachments/api}")
    private val serverUrl: String? = null

    @Bean
    fun customOpenAPI(): OpenAPI? {
        return OpenAPI()
            .addServersItem(Server().url(serverUrl))
            .addSecurityItem(SecurityRequirement().addList("bearerAuth"))
//            .components(apiSecurity())
            .info(apiInfo())
    }

    private fun apiInfo(): Info? {
        return Info()
            .title("Comms Ms CRM Attachments")
            .description("This is a REST API with the CRM attachments operations")
            .version("1.0")
    }

//    private fun apiSecurity(): Components? {
//        return Components()
//            .addSecuritySchemes(
//                "bearerAuth",
//                SecurityScheme()
//                    .type(SecurityScheme.Type.HTTP)
//                    .bearerFormat("jwt")
//                    .scheme("Bearer")
//                    .`in`(SecurityScheme.In.HEADER)
//                    .name("Authorization")
//            )
//    }

}