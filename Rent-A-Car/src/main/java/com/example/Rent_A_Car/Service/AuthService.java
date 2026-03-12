package com.example.Rent_A_Car.Service;

import com.example.Rent_A_Car.Config.JwtUtil;
import com.example.Rent_A_Car.DTO.LoginRequest;
import com.example.Rent_A_Car.DTO.LoginResponse;
import com.example.Rent_A_Car.DTO.RegisterRequest;
import com.example.Rent_A_Car.Model.User;
import com.example.Rent_A_Car.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;


    // ── Register (unchanged) ──────────────────────────────────────────

    public User register(RegisterRequest req) {

        if (userRepository.existsByEmail(req.getEmail())) {
            throw new RuntimeException("Email is already registered");
        }

        long age = ChronoUnit.YEARS.between(req.getDateOfBirth(), LocalDate.now());
        if (age < 18) {
            throw new RuntimeException("Must be 18 or older to rent");
        }

        if (!req.getPassword().equals(req.getConfirmPassword())) {
            throw new RuntimeException("Passwords do not match");
        }

        if (!req.isTermsAccepted()) {
            throw new RuntimeException("You must accept the Terms of Service");
        }

        String hashedPassword = passwordEncoder.encode(req.getPassword());

        User user = new User(
                req.getFirstName(),
                req.getLastName(),
                req.getEmail(),
                hashedPassword,
                req.getPhone(),
                req.getDateOfBirth(),
                req.getCountry(),
                req.getCity(),
                req.getStreet(),
                req.getState(),
                req.isTermsAccepted(),
                req.isMarketingConsent()
        );

        return userRepository.save(user);
    }


    // ── Login ─────────────────────────────────────────────────────────

    public LoginResponse login(LoginRequest req) {

        // 1. find user by email
        User user = userRepository.findByEmail(req.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid email or password"));

        // 2. check password against stored BCrypt hash
        if (!passwordEncoder.matches(req.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid email or password");
            // note: same message for both cases — don't reveal which was wrong
        }

        // 3. generate JWT token
        String token = jwtUtil.generateToken(user.getEmail());

        // 4. return token + basic user info
        return new LoginResponse(
                token,
                user.getUserId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getAvatarUrl()
        );
    }
}