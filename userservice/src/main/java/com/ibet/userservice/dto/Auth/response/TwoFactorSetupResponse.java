package com.ibet.userservice.dto.Auth.response;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;

public class TwoFactorSetupResponse {

    @Schema(description = "QR code data URL for authenticator app")
    private String qrCodeDataUrl;
    
    @Schema(description = "Secret key for manual setup")
    private String secretKey;
    
    @Schema(description = "Recovery codes (show only once)")
    private List<String> recoveryCodes;
    
    @Schema(description = "Device name if provided")
    private String deviceName;

    @Schema(description = "Setup completion timestamp")
    private String setupTime;
    
}
