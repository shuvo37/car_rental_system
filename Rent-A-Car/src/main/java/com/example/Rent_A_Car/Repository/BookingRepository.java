package com.example.Rent_A_Car.Repository;

import com.example.Rent_A_Car.Model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByUserUserId(Long userId);   // 👈 get all bookings for a user
    List<Booking> findByCarCarId(Long carId);      // 👈 get all bookings for a car
    long countByPromoAppliedTrue();

}
