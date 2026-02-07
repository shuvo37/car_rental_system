package com.example.Rent_A_Car.Repository;


import com.example.Rent_A_Car.Model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    List<Payment> findByUserUserId(Long userId);

}
