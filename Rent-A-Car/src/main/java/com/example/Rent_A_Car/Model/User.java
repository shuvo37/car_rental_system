package com.example.Rent_A_Car.Model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(
        name = "users",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "email")
        }
)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    // CHANGED: split single "name" into two — matches frontend firstName + lastName
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String email;                    // no change

    @Column(nullable = false)
    private String password;                 // no change

    @Column(name = "phone_number")
    private String phoneNumber;              // no change

    // REMOVED: gender — not in your frontend form at all

    // ADDED: matches frontend "dob" field
    @Column(name = "date_of_birth", nullable = false)
    private LocalDate dateOfBirth;

    // ADDED: avatar stored as URL/path after file upload
    @Column(name = "avatar_url")
    private String avatarUrl = "default-avatar.png";

    // ADDED: Step 2 address fields
    @Column(nullable = false)
    private String country;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String street;

    @Column(nullable = true)
    private String state;                    // optional in frontend

    // ADDED: matches frontend "terms" checkbox — required
    @Column(name = "terms_accepted", nullable = false)
    private boolean termsAccepted;

    // ADDED: matches frontend "marketing" checkbox — optional
    @Column(name = "marketing_consent")
    private boolean marketingConsent;

    // ADDED: for email verification flow (success screen message)
    @Column(name = "email_verified")
    private boolean emailVerified = false;

    @Column(name = "created_at")
    private LocalDateTime createdAt;         // no change


    public User() {}

    // UPDATED constructor to match new fields
    public User(String firstName, String lastName, String email, String password,
                String phoneNumber, LocalDate dateOfBirth, String country,
                String city, String street, String state,
                boolean termsAccepted, boolean marketingConsent) {

        this.firstName       = firstName;
        this.lastName        = lastName;
        this.email           = email;
        this.password        = password;
        this.phoneNumber     = phoneNumber;
        this.dateOfBirth     = dateOfBirth;
        this.country         = country;
        this.city            = city;
        this.street          = street;
        this.state           = state;
        this.termsAccepted   = termsAccepted;
        this.marketingConsent = marketingConsent;
        this.emailVerified   = false;
        this.createdAt       = LocalDateTime.now();
    }


    // ── Getters & Setters ──

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public LocalDate getDateOfBirth() { return dateOfBirth; }
    public void setDateOfBirth(LocalDate dateOfBirth) { this.dateOfBirth = dateOfBirth; }

    public String getAvatarUrl() { return avatarUrl; }
    public void setAvatarUrl(String avatarUrl) { this.avatarUrl = avatarUrl; }

    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public String getStreet() { return street; }
    public void setStreet(String street) { this.street = street; }

    public String getState() { return state; }
    public void setState(String state) { this.state = state; }

    public boolean isTermsAccepted() { return termsAccepted; }
    public void setTermsAccepted(boolean termsAccepted) { this.termsAccepted = termsAccepted; }

    public boolean isMarketingConsent() { return marketingConsent; }
    public void setMarketingConsent(boolean marketingConsent) { this.marketingConsent = marketingConsent; }

    public boolean isEmailVerified() { return emailVerified; }
    public void setEmailVerified(boolean emailVerified) { this.emailVerified = emailVerified; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}