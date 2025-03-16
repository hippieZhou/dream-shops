package com.hippiezhou.dreamshops.remote.core;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.http.codec.json.Jackson2JsonEncoder;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

public class WebClientFactory {
    private static final int MAX_IN_MEMORY_SIZE = 1024 * 1024;
    private static final int CONNECT_TIMEOUT_SECONDS = 30;

    public static WebClient.Builder getWebClientBuilder(ObjectMapper mapper) {
        return WebClient.builder()
            .clientConnector(new ReactorClientHttpConnector(getHttpClient()))
            .exchangeStrategies(getExchangeStrategies(mapper));
    }

    private static HttpClient getHttpClient() {
        return HttpClient.create()
            .wiretap(true)
            .compress(true)
            .followRedirect(true)
            .responseTimeout(java.time.Duration.ofSeconds(CONNECT_TIMEOUT_SECONDS));
    }

    private static ExchangeStrategies getExchangeStrategies(ObjectMapper mapper) {
        return ExchangeStrategies.builder()
            .codecs(configurer -> {
                configurer.defaultCodecs().maxInMemorySize(MAX_IN_MEMORY_SIZE);
                configurer.defaultCodecs().jackson2JsonDecoder(new Jackson2JsonDecoder(mapper));
                configurer.defaultCodecs().jackson2JsonEncoder(new Jackson2JsonEncoder(mapper));
            })
            .build();
    }
}
