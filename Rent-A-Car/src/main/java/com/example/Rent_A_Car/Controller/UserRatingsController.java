package com.example.Rent_A_Car.Controller;

import com.example.Rent_A_Car.Model.UserRating;
import com.example.Rent_A_Car.Repository.UserRatingRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/ratings")
public class UserRatingsController {

    private final UserRatingRepository userRatingsRepository;

    public UserRatingsController(UserRatingRepository userRatingsRepository) {
        this.userRatingsRepository = userRatingsRepository;
    }


    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getRatingByUser(@PathVariable Long userId) {
        Optional<UserRating> rating = userRatingsRepository.findByUserUserId(userId);
        return rating
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @PutMapping("/user/{userId}")
    public ResponseEntity<?> updateRating(@PathVariable Long userId,
                                          @RequestBody UserRating updatedRating) {

        Optional<UserRating> optionalRating = userRatingsRepository.findByUserUserId(userId);

        if (optionalRating.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        UserRating rating = optionalRating.get();
        rating.setRating(updatedRating.getRating());
        rating.setDiscountPercentage(updatedRating.getDiscountPercentage());

        return ResponseEntity.ok(userRatingsRepository.save(rating));
    }
}