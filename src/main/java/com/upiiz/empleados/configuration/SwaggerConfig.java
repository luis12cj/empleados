package com.upiiz.empleados.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "API de Empleados",
                version = "1.0.0",
                description = "Documentación de la API para la gestión de empleados con Arquitectura por Capas",
                termsOfService = "http://swagger.io/terms/",
                contact = @Contact(
                        name = "Luis Eduardo Carrillo Jaime",
                        email = "lcarrilloj@alumno.ipn.mx",
                        url = "https://github.com/luis1205cj"
                ),
                license = @License(
                        name = "Apache 2.0",
                        url = "http://springdoc.org"
                )
        ),
        servers = {
                @Server(url = "http://localhost:8080", description = "Servidor Local")
        }
)
public class SwaggerConfig {
}