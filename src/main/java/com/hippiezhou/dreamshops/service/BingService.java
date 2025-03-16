package com.hippiezhou.dreamshops.service;

import com.hippiezhou.dreamshops.dto.bing.BingResponse;
import reactor.core.publisher.Mono;

public interface BingService {
    Mono<BingResponse> getBingImage(Integer n);
}
