package com.hippiezhou.dreamshops.remote;

import com.hippiezhou.dreamshops.remote.dto.Images;
import reactor.core.publisher.Mono;

import java.util.List;

public interface BingClient {
    Mono<List<Images>> getBingImage(Integer n);
}
