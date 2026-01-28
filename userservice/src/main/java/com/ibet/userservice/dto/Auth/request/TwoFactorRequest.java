package com.ibet.userservice.dto.Auth.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class TwoFactorRequest {
    @NotBlank(message = "Password is required")
    @Schema(description = "User password for verification", example = "Passw0rd!")
    private String password;
    
    @Schema(description = "Optional: device name for 2FA", example = "iPhone 12")
    private String deviceName;
}
