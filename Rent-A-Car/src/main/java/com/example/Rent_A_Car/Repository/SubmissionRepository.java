package com.example.Rent_A_Car.Repository;

import com.example.Rent_A_Car.Model.Submission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SubmissionRepository extends JpaRepository<Submission, Long> {

    Optional<Submission> findByBookingBookingId(Long bookingId);

}
