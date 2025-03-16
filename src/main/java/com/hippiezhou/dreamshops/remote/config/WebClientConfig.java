package com.hippiezhou.dreamshops.remote.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.hippiezhou.dreamshops.remote.core.WebClientFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@RequiredArgsConstructor
public class WebClientConfig {
    private final ObjectMapper mapper;

    @Value("${bing.base-url}")
    private String baseUrl;

    @Bean
    public WebClient getBingClient() {
        ObjectMapper camelCaseMapper = mapper.copy().setPropertyNamingStrategy(PropertyNamingStrategies.LOWER_CAMEL_CASE);
        return WebClientFactory.getWebClientBuilder(camelCaseMapper)
            .baseUrl(baseUrl)
            .build();
    }
}
