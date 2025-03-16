package com.hippiezhou.dreamshops.service.impl;

import com.hippiezhou.dreamshops.dto.bing.BingResponse;
import com.hippiezhou.dreamshops.remote.BingClient;
import com.hippiezhou.dreamshops.service.BingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class BingServiceImpl implements BingService {
    private final BingClient bingClient;

    public Mono<BingResponse> getBingImage(Integer n) {
        return bingClient.getBingImage(n).map(BingResponse::new);
    }
}
