package com.hippiezhou.dreamshops.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "User create request")
public record UserCreateResponse(
    @Schema(
        requiredMode = Schema.RequiredMode.REQUIRED,
        description = "Unique identifier",
        example = "1")
    Long id,
    @Schema(
        requiredMode = Schema.RequiredMode.REQUIRED,
        description = "User first name",
        example = "John")
    String firstName,
    @Schema(
        requiredMode = Schema.RequiredMode.REQUIRED,
        description = "User last name",
        example = "Doe")
    String lastName,
    @Schema(
        requiredMode = Schema.RequiredMode.REQUIRED,
        description = "User email",
        example = "hello@example.com")
    String email) {
}
