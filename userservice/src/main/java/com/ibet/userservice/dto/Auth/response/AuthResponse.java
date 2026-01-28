package com.ibet.userservice.dto.Auth.response;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Data
public class AuthResponse {
    @Schema(description = "JWT access token")
    private String accessToken;
    
    @Schema(description = "Refresh token for obtaining new access tokens")
    private String refreshToken;
    
    @Schema(description = "Token type", example = "Bearer")
    private String tokenType;
    
    @Schema(description = "Access token expiration timestamp")
    private Instant expiresAt;

    @Schema(description = "Authenticated user ID")
    private UUID userId;
    
    @Schema(description = "User email")
    private String email;
    
    @Schema(description = "User roles")
    private List<String> roles;
    
    @Schema(description = "User permissions")
    private List<String> permissions;

    @Schema(description = "Whether user needs to complete KYC verification")
    private boolean kycRequired;
    
    @Schema(description = "Whether two-factor authentication is enabled")
    private boolean twoFactorEnabled;
}
