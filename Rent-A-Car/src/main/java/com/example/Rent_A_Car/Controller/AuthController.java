package com.example.Rent_A_Car.Controller;

import com.example.Rent_A_Car.Model.User;
import com.example.Rent_A_Car.Repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin
public class AuthController {

    private final UserRepository userRepository;

    public AuthController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @PostMapping("/register")
    public ResponseEntity<?>register(@RequestBody User user)
    {
        if(userRepository.findByEmail(user.getEmail()).isPresent())
        {
            return ResponseEntity.badRequest().body("User already exists");
        }

        String phone = user.getPhoneNumber();

        boolean isValid =
                        phone != null &&
                        phone.length() == 11 &&
                        phone.startsWith("01") &&
                        phone.matches("[0-9]+");

        if(user.getPassword().length() < 3)
        {

            return ResponseEntity.badRequest().body("Password too short");
        }
        else if (!isValid)
        {
            return ResponseEntity.badRequest().body("Error in phone number");
        }

        user.setCreatedAt(LocalDateTime.now());

        User savedUser = userRepository.save(user);

        return ResponseEntity.ok(savedUser);

    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User loginRequest) {

        Optional<User> userOpt = userRepository.findByEmail(loginRequest.getEmail());

        if (userOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("Invalid email");
        }

        User user = userOpt.get();

        if (!user.getPassword().equals(loginRequest.getPassword())) {
            return ResponseEntity.badRequest().body("Invalid password");
        }

        return ResponseEntity.ok(user);
    }



}