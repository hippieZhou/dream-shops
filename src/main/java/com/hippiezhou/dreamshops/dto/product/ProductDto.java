package com.hippiezhou.dreamshops.dto.product;

import com.hippiezhou.dreamshops.dto.image.ImageDto;
import com.hippiezhou.dreamshops.model.Category;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.util.List;

@Schema(description = "Product information")
public record ProductDto(
    @Schema(
        requiredMode = Schema.RequiredMode.REQUIRED,
        description = "Unique identifier",
        example = "1")
    Long id,
    @Schema(
        requiredMode = Schema.RequiredMode.REQUIRED,
        description = "Product name",
        example = "iPhone 12")
    String name,
    @Schema(
        requiredMode = Schema.RequiredMode.REQUIRED,
        description = "Product brand",
        example = "Apple")
    String brand,
    @Schema(
        requiredMode = Schema.RequiredMode.REQUIRED,
        description = "Product price",
        example = "999.99")
    BigDecimal price,
    @Schema(
        requiredMode = Schema.RequiredMode.REQUIRED,
        description = "Product inventory",
        example = "100")
    int inventory,
    @Schema(
        requiredMode = Schema.RequiredMode.REQUIRED,
        description = "Product description",
        example = "The latest iPhone model")
    String description,
    @Schema(
        requiredMode = Schema.RequiredMode.REQUIRED,
        description = "Product category",
        example = "Electronics")
    Category category,
    @Schema(
        requiredMode = Schema.RequiredMode.REQUIRED,
        description = "Product images"
    )
    List<ImageDto> images) {
}
