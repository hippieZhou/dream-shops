package com.hippiezhou.dreamshops;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
    info = @Info(title = "Dream Shops", version = "3.1.0"),
    servers = {
        @Server(url = "http://localhost:8080", description = "Local"),
    })
public class DreamShopsApplication {

    public static void main(String[] args) {
        SpringApplication.run(DreamShopsApplication.class, args);
    }

}
