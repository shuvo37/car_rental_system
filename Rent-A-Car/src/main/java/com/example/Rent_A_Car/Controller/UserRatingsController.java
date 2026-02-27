package com.example.Rent_A_Car.Controller;

import com.example.Rent_A_Car.Model.UserRating;
import com.example.Rent_A_Car.Repository.CarRepository;
import com.example.Rent_A_Car.Repository.UserRatingRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "*")   // 👈 added
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

        // 👇 updated all new fields
        rating.setRating(updatedRating.getRating());
        rating.setLatePenalty(updatedRating.getLatePenalty());
        rating.setDiscountPercentage(updatedRating.getDiscountPercentage());
        rating.setDiscountEnabled(updatedRating.getDiscountEnabled());
        rating.setDiscountType(updatedRating.getDiscountType());
        rating.setDiscountValue(updatedRating.getDiscountValue());
        rating.setDiscountMinOrders(updatedRating.getDiscountMinOrders());
        rating.setDiscountMaxUses(updatedRating.getDiscountMaxUses());

        return ResponseEntity.ok(userRatingsRepository.save(rating));
    }
}