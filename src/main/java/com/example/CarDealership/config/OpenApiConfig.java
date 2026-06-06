package com.example.CarDealership.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * OpenAPI Configuration for Swagger/Springdoc Documentation.
 * 
 * This configuration generates comprehensive API documentation automatically.
 * Access the Swagger UI at: http://localhost:8080/swagger-ui.html
 * Access the OpenAPI JSON at: http://localhost:8080/v3/api-docs
 */
@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Car Dealership Management System API")
                        .description("A professional Spring Boot 3.x REST API for managing a car dealership system. "
                                + "Features include self-referencing location hierarchies (Province > District > Sector > Cell > Village), "
                                + "customer and employee management, car inventory tracking, and sales transactions with many-to-many relationships.")
                        .version("2.0.0")
                        .contact(new Contact()
                                .name("Jonathan Mukunzi")
                                .url("https://github.com/mukunzijonathan/CDMS")
                                .email("support@cardealership.com"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0.html")));
    }
}
