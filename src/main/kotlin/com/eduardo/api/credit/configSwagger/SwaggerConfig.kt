package com.eduardo.api.credit.configSwagger

import org.springdoc.core.models.GroupedOpenApi
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class SwaggerConfig {

    @Bean
    fun publicApi(): GroupedOpenApi?{
        return GroupedOpenApi.builder()
            .group(".com.eduardo.api.credit")
            .pathsToMatch("/api/customers/**", "/api/credits/**")
            .build()
    }
}