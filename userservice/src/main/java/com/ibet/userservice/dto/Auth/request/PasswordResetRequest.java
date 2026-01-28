package com.ibet.userservice.dto.Auth.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class PasswordResetRequest {

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    @Schema(description = "User email address", example = "user@example.com")
    private String email;
    
    @Schema(description = "Optional: URL for password reset page", example = "https://app.betting.com/reset-password")
    private String resetUrl;
    
}
