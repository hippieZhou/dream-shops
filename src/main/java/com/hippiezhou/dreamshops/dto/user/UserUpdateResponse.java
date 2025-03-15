package com.hippiezhou.dreamshops.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "User update request")
public record UserUpdateResponse(
    @Schema(
        requiredMode = Schema.RequiredMode.REQUIRED,
        description = "Unique identifier",
        example = "1")
    Long id,
    @Schema(
        requiredMode = Schema.RequiredMode.REQUIRED,
        description = "User first name",
        example = "John")
    @NotBlank
    String firstName,
    @Schema(
        requiredMode = Schema.RequiredMode.REQUIRED,
        description = "User last name",
        example = "Doe")
    @NotBlank
    String lastName) {
}
