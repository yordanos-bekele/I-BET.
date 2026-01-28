package com.ibet.userservice.services;

import java.util.UUID;

import com.ibet.userservice.dto.Auth.request.AuthRequest;
import com.ibet.userservice.dto.Auth.request.RefreshTokenRequest;
import com.ibet.userservice.dto.Auth.request.TwoFactorRequest;
import com.ibet.userservice.dto.Auth.request.TwoFactorVerificationRequest;
import com.ibet.userservice.dto.Auth.response.AuthResponse;

import jakarta.servlet.http.HttpServletRequest;

public interface AuthenticationService {

    AuthResponse authenticate(AuthRequest request, HttpServletRequest httpRequest);
    AuthResponse refreshToken(RefreshTokenRequest request, HttpServletRequest httpRequest);
    void logout(String refreshToken, HttpServletRequest httpRequest);
    void logoutAllDevices(UUID userId);
    void validateToken(String token);
    AuthResponse enableTwoFactor(UUID userId, TwoFactorRequest request);
    AuthResponse verifyTwoFactor(UUID userId, TwoFactorVerificationRequest request);
    void disableTwoFactor(UUID userId);
    
}
