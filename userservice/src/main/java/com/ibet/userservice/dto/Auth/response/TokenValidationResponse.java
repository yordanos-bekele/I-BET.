package com.ibet.userservice.dto.Auth.response;

import java.time.Instant;
import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;

public class TokenValidationResponse {
    
    @Schema(description = "Whether token is valid")
    private boolean valid;
    
    @Schema(description = "Token subject (username/email)")
    private String subject;
    
    @Schema(description = "User ID")
    private UUID userId;

    @Schema(description = "Token expiration timestamp")
    private Instant expiresAt;
    
    @Schema(description = "Token issuer")
    private String issuer;
    
    @Schema(description = "List of scopes/roles")
    private String[] scopes;
    
    @Schema(description = "Validation error message if invalid")
    private String error;
}
