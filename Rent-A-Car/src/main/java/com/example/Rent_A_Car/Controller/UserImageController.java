package com.example.Rent_A_Car.Controller;

import com.example.Rent_A_Car.Model.UserImage;
import com.example.Rent_A_Car.Repository.UserImageRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/user-images")
public class UserImageController {

    private final UserImageRepository userImageRepository;

    public UserImageController(UserImageRepository userImageRepository) {
        this.userImageRepository = userImageRepository;
    }


    @PostMapping
    public ResponseEntity<?> createDefaultImage(@RequestBody UserImage userImage) {
        // optional: set default avatar if image_url is null
        if (userImage.getImageUrl() == null || userImage.getImageUrl().isEmpty()) {
            userImage.setImageUrl("default_avatar.png");
        }

        return ResponseEntity.ok(userImageRepository.save(userImage));
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> updateImage(@PathVariable Long id, @RequestBody UserImage updatedImage) {
        Optional<UserImage> optionalImage = userImageRepository.findById(id);

        if (optionalImage.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        UserImage image = optionalImage.get();
        image.setImageUrl(updatedImage.getImageUrl());

        return ResponseEntity.ok(userImageRepository.save(image));
    }


    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getImageByUser(@PathVariable Long userId) {
        Optional<UserImage> image = userImageRepository.findByUserUserId(userId);
        return image
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
