package com.hippiezhou.dreamshops.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "User create request")
public record UserCreateRequest(
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
    String lastName,
    @Schema(
        requiredMode = Schema.RequiredMode.REQUIRED,
        description = "User email",
        example = "test@example.com")
    @NotBlank
    String email,
    @Schema(
        requiredMode = Schema.RequiredMode.REQUIRED,
        description = "User password",
        example = "password")
    @NotBlank
    String password) {
}
