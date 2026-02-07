package com.example.Rent_A_Car.Controller;
import com.example.Rent_A_Car.Model.Payment;
import com.example.Rent_A_Car.Repository.PaymentRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentRepository paymentRepository;

    public PaymentController(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }


    @PostMapping
    public ResponseEntity<?> createPayment(@RequestBody Payment payment) {
        // set server-side fields
        payment.setPaymentTime(LocalDateTime.now());
        payment.setPaymentStatus(payment.getPaymentStatus()); // usually PENDING
        return ResponseEntity.ok(paymentRepository.save(payment));
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getPaymentById(@PathVariable Long id) {
        Optional<Payment> payment = paymentRepository.findById(id);
        return payment
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Payment>> getPaymentsByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(paymentRepository.findByUserUserId(userId));
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> updatePaymentStatus(@PathVariable Long id,
                                                 @RequestBody Payment updatedPayment) {

        Optional<Payment> optionalPayment = paymentRepository.findById(id);

        if (optionalPayment.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Payment payment = optionalPayment.get();
        payment.setPaymentStatus(updatedPayment.getPaymentStatus());
        payment.setPaymentMethod(updatedPayment.getPaymentMethod());

        return ResponseEntity.ok(paymentRepository.save(payment));
    }
}
