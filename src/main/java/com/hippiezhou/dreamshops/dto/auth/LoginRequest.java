package com.hippiezhou.dreamshops.dto.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "Login request")
public record LoginRequest(
    @Schema(
        requiredMode = Schema.RequiredMode.REQUIRED,
        description = "User email",
        example = "user@example.com")
    @Email
    String email,
    @Schema(
        requiredMode = Schema.RequiredMode.REQUIRED,
        description = "User password",
        example = "password")
    @NotBlank
    String password) {
}
