package com.example.Rent_A_Car.Repository;

import com.example.Rent_A_Car.Model.UserRating;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRatingRepository extends JpaRepository<UserRating, Long> {

    Optional<UserRating> findByUserUserId(Long userId);
}
