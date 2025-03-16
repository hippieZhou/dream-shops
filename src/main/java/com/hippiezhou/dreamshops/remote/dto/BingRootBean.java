package com.hippiezhou.dreamshops.remote.dto;

import java.util.List;

public record BingRootBean(
    List<Images> images,
    Tooltips tooltips) {
}
