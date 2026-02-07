package com.example.Rent_A_Car.Repository;

import com.example.Rent_A_Car.Model.UserImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserImageRepository extends JpaRepository<UserImage, Long> {

    Optional<UserImage> findByUserUserId(Long userId);
}
