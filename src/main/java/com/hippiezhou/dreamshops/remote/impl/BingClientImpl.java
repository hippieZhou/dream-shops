package com.hippiezhou.dreamshops.remote.impl;

import com.hippiezhou.dreamshops.remote.BingClient;
import com.hippiezhou.dreamshops.remote.dto.BingRootBean;
import com.hippiezhou.dreamshops.remote.dto.Images;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BingClientImpl implements BingClient {
    public static final String IMAGE_ARCHIVE = "/HPImageArchive.aspx?format=js&idx=0&n={n}&mkt=zh-CN";
    private final WebClient bingClient;

    @Override
    public Mono<List<Images>> getBingImage(Integer n) {
        return bingClient.get()
            .uri(IMAGE_ARCHIVE, n)
            .retrieve()
            .bodyToMono(BingRootBean.class).map(BingRootBean::images);
    }
}
