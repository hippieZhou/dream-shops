package com.hippiezhou.dreamshops.dto.bing;

import com.hippiezhou.dreamshops.remote.dto.Images;

import java.util.List;

public record BingResponse(
    List<Images> images) {
}

