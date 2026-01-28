package com.ibet.userservice.dto.user.request;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Data
public class UserRegistrationDto {
    
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    @Schema(description = "User email address", example = "user@example.com")
    private String email;
    
    @NotBlank(message = "Username is required")
    @Size(min = 3, max = 50, message = "Username must be 3-50 characters")
    @Pattern(regexp = "^[a-zA-Z0-9._-]+$", message = "Username can only contain letters, numbers, dots, underscores and hyphens")
    @Schema(description = "Unique username", example = "trader_john")
    private String username;

    @NotBlank(message = "Password is required")
    @Size(min = 8, max = 100, message = "Password must be 8-100 characters")
    @Schema(description = "User password", example = "Passw0rd!")
    private String password;
    
    @NotBlank(message = "First name is required")
    @Size(max = 100, message = "First name too long")
    @Schema(description = "User first name", example = "John")
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Size(max = 100, message = "Last name too long")
    @Schema(description = "User last name", example = "Doe")
    private String lastName;
    
    @NotNull(message = "Date of birth is required")
    @Past(message = "Date of birth must be in the past")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Schema(description = "Date of birth", example = "1990-01-01", type = "string")
    private LocalDate dateOfBirth;

    @NotBlank(message = "Country code is required")
    @Pattern(regexp = "^[A-Z]{2}$", message = "Country code must be 2 uppercase letters")
    @Schema(description = "ISO 3166-1 alpha-2 country code", example = "US")
    private String countryCode;
    
    @Pattern(regexp = "^\\+?[1-9]\\d{1,14}$", message = "Invalid phone number format")
    @Schema(description = "Phone number in E.164 format", example = "+1234567890")
    private String phoneNumber;
    
    @Pattern(regexp = "^[A-Z]{3}$", message = "Currency code must be 3 uppercase letters")
    @Schema(description = "ISO 4217 currency code", example = "USD")
    private String currencyCode;

    @Pattern(regexp = "^[a-z]{2}$", message = "Language code must be 2 lowercase letters")
    @Schema(description = "ISO 639-1 language code", example = "en")
    private String languageCode;
    
    @Schema(description = "Whether user accepts marketing communications")
    private boolean marketingConsent;
    
    @AssertTrue(message = "Must accept terms and conditions")
    @Schema(description = "Terms and conditions acceptance")
    private boolean acceptedTerms;

}
