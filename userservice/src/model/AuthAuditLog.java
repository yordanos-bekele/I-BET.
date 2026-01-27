package com.ibet.userservice.model;

import java.time.LocalDateTime;
import java.util.UUID;

import com.ibet.userservice.enums.AuthEventType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "auth_audit_log")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthAuditLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "BINARY(16)")
    private UUID auditId;

    @Column(columnDefinition = "BINARY(16)")
    private UUID userId;

    @Enumerated(EnumType.STRING)
    private AuthEventType eventType;

    private String ipAddress;

    @Column(columnDefinition = "TEXT")
    private String userAgent;

    private String deviceFingerprint;

    @Column(columnDefinition = "JSON")
    private String locationData;

    @Column(nullable = false)
    private LocalDateTime eventTimestamp = LocalDateTime.now();

    private Boolean success;

    private String details;
}
