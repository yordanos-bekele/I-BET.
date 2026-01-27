package com.ibet.userservice.model;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(name = "first_name")
    private String FirstName;

    @Column(name="last_name")
    private String LastName;

    @Column(name="date_of_birth")
    private LocalDateTime dataOfBirth;

    @Column(length = 2)
    private String countryCode;

    @Column(length = 3)
    private String currencyCode = "USD";

    @Column(length = 2)
    private String languageCode = "EN";

    private String timezone = "UTC";

    private boolean marketingConsent = false;

    private boolean twoFactorEnabled = false;

    private String twoFactorSecret;

}
