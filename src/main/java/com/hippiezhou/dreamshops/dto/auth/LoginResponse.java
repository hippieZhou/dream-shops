package com.hippiezhou.dreamshops.dto.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "Login response")
public record LoginResponse(
    @Schema(description = "User ID",
        example = "1")
    @NotBlank
    Long id,
    @Schema(description = "JWT token",
        example = "eyJhbGciOiJIUzUxMiJ9")
    @NotBlank
    String token) {
}
