package com.example.Rent_A_Car.Controller;

import com.example.Rent_A_Car.DTO.LoginRequest;
import com.example.Rent_A_Car.DTO.LoginResponse;
import com.example.Rent_A_Car.DTO.RegisterRequest;
import com.example.Rent_A_Car.Model.User;
import com.example.Rent_A_Car.Repository.UserRepository;
import com.example.Rent_A_Car.Service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private UserRepository userRepository;

    @Value("${app.avatar.upload-dir:uploads/avatars}")
    private String uploadDir;


    // ── Register ──────────────────────────────────────────────────────

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        try {
            User saved = authService.register(request);  // now returns User
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(Map.of(
                            "message", "Account created successfully",
                            "userId",  saved.getUserId()   // frontend needs this to upload avatar
                    ));
        } catch (RuntimeException e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", e.getMessage()));
        }
    }


    // ── Upload Avatar ─────────────────────────────────────────────────
    // POST /api/auth/avatar/{userId}

    @PostMapping("/avatar/{userId}")
    public ResponseEntity<?> uploadAvatar(
            @PathVariable Long userId,
            @RequestParam("file") MultipartFile file) {

        // 1. find user
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", "User not found"));
        }

        // 2. images only
        String contentType = file.getContentType();
        if (contentType == null || !contentType.startsWith("image/")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", "Only image files are allowed"));
        }

        try {
            // 3. create folder if missing
            Path uploadPath = Paths.get(uploadDir);
            Files.createDirectories(uploadPath);

            // 4. unique filename  e.g. "a3f9c1d2-uuid.png"
            String original = file.getOriginalFilename();
            String extension = original.substring(original.lastIndexOf("."));
            String fileName = UUID.randomUUID() + extension;

            // 5. delete old avatar from disk if not the default
            if (user.getAvatarUrl() != null && !user.getAvatarUrl().equals("default-avatar.png")) {
                Files.deleteIfExists(uploadPath.resolve(user.getAvatarUrl()));
            }

            // 6. save file to disk
            Files.copy(file.getInputStream(),
                    uploadPath.resolve(fileName),
                    StandardCopyOption.REPLACE_EXISTING);

            // 7. save filename to DB
            user.setAvatarUrl(fileName);
            userRepository.save(user);

            return ResponseEntity.ok(Map.of(
                    "message",   "Avatar uploaded successfully",
                    "avatarUrl", fileName
            ));

        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Failed to save image"));
        }
    }


    // ── Serve Avatar ──────────────────────────────────────────────────
    // GET /api/auth/avatar/{fileName}
    // used directly as <img src="http://localhost:8080/api/auth/avatar/xxx.png" />

    @GetMapping("/avatar/{fileName}")
    public ResponseEntity<Resource> getAvatar(@PathVariable String fileName) {
        try {
            Path filePath;

            if (fileName.equals("default-avatar.png")) {
                // served from src/main/resources/static/default-avatar.png
                filePath = Paths.get("src/main/resources/static/default-avatar.png");
            } else {
                filePath = Paths.get(uploadDir).resolve(fileName);
            }

            Resource resource = new UrlResource(filePath.toUri());

            // fallback to default if file not found on disk
            if (!resource.exists()) {
                filePath = Paths.get("src/main/resources/static/default-avatar.png");
                resource = new UrlResource(filePath.toUri());
            }

            String contentType = Files.probeContentType(filePath);
            if (contentType == null) contentType = "image/jpeg";

            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(contentType))
                    .header(HttpHeaders.CONTENT_DISPOSITION,
                            "inline; filename=\"" + fileName + "\"")
                    .body(resource);

        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try {
            LoginResponse response = authService.login(request);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", e.getMessage()));
        }
    }



}