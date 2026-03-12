package com.example.Rent_A_Car.DTO;

public class LoginResponse {

    private String token;       // JWT token — frontend stores this
    private Long userId;
    private String firstName;
    private String lastName;
    private String email;
    private String avatarUrl;

    public LoginResponse(String token, Long userId, String firstName,
                         String lastName, String email, String avatarUrl) {
        this.token     = token;
        this.userId    = userId;
        this.firstName = firstName;
        this.lastName  = lastName;
        this.email     = email;
        this.avatarUrl = avatarUrl;
    }

    public String getToken()      { return token; }
    public Long getUserId()       { return userId; }
    public String getFirstName()  { return firstName; }
    public String getLastName()   { return lastName; }
    public String getEmail()      { return email; }
    public String getAvatarUrl()  { return avatarUrl; }
}