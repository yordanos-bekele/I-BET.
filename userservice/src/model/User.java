package com.ibet.userservice.model;

import com.ibet.userservice.enums.AccountTier;
import com.ibet.userservice.enums.Status;
import com.ibet.userservice.enums.UserRole;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class User extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    @Column(unique = true)
    private String email;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id")
    private UserProfile profile;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "document_id")
    private KycDocument kycDocument;

    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    @Column(unique = true, nullable = false, name = "password_hash")
    private String passwordHash;

    @Column(unique = true, name = "phone_number")
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private Status status = Status.PENDING;

    @Enumerated(EnumType.STRING)
    private AccountTier accountTier = AccountTier.BRONZE;

    @Column(name = "failed_login_attempts")
    private int failedLoginAttempts;

    @Column(name = "locked_until")
    private LocalDateTime lockedUntil;

}

