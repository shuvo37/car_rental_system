package com.example.Rent_A_Car.Repository;

import com.example.Rent_A_Car.Model.RatingPolicy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RatingPolicyRepository extends JpaRepository<RatingPolicy, Long> {
}