package com.ibet.userservice.dto.Auth.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class PasswordChangeRequest {
    
    @NotBlank(message = "Current password is required")
    @Schema(description = "Current password", example = "OldPassw0rd!")
    private String currentPassword;
    
    @NotBlank(message = "New password is required")
    @Size(min = 8, max = 100, message = "New password must be 8-100 characters")
    @Schema(description = "New password", example = "NewPassw0rd!")
    private String newPassword;
    
    @NotBlank(message = "Password confirmation is required")
    @Schema(description = "Confirm new password", example = "NewPassw0rd!")
    private String confirmPassword;

}
