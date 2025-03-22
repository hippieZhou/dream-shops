package com.hippiezhou.dreamshops;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
    info = @Info(
        title = "Dream Shops",
        description = "OpenApi documentation for Dream Shops",
        version = "1.0",
        contact = @Contact(name = "Hippie Zhou", email = "hippiezhou@outlook.com"),
        license = @License(name = "Apache 2.0", url = "http://www.apache.org/licenses/LICENSE-2.0.html"),
        termsOfService = "http://swagger.io/terms/"
    ),
    servers = {
        @Server(url = "http://localhost:8080", description = "LocalENV"),
    },
    security = {
        @SecurityRequirement(name = "bearerAuth")
    })
@SecurityScheme(
    name = "bearerAuth",
    description = "JWT auth description",
    scheme = "bearer",
    type = SecuritySchemeType.HTTP,
    bearerFormat = "JWT",
    in = SecuritySchemeIn.HEADER
)
public class DreamShopsApplication {

    public static void main(String[] args) {
        SpringApplication.run(DreamShopsApplication.class, args);
    }

}
