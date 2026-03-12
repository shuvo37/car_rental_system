package com.example.Rent_A_Car.Controller;

import com.example.Rent_A_Car.DTO.BookingRequest;
import com.example.Rent_A_Car.Model.*;
import com.example.Rent_A_Car.Repository.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    private final BookingRepository      bookingRepository;
    private final CarRepository          carRepository;
    private final UserRepository         userRepository;
    private final RatingPolicyRepository policyRepository;

    public BookingController(BookingRepository bookingRepository,
                             CarRepository carRepository,
                             UserRepository userRepository,
                             RatingPolicyRepository policyRepository) {
        this.bookingRepository = bookingRepository;
        this.carRepository     = carRepository;
        this.userRepository    = userRepository;
        this.policyRepository  = policyRepository;
    }

    @PostMapping
    public ResponseEntity<?> createBooking(@RequestBody BookingRequest request) {

        User user = userRepository.findById(request.getUserId()).orElse(null);
        if (user == null) return ResponseEntity.badRequest().body("User not found");

        Car car = carRepository.findById(request.getCarId()).orElse(null);
        if (car == null) return ResponseEntity.badRequest().body("Car not found");

        RatingPolicy policy = policyRepository.findById(1L).orElse(null);

        // validate discount on backend — ignore whatever frontend sent
        double validatedDiscount = 0.0;

        if (policy != null && policy.getDiscountEnabled() && Boolean.TRUE.equals(request.getPromoApplied())) {

            long completedBookings = bookingRepository.findByUserUserId(request.getUserId()).size();
            if (completedBookings < policy.getDiscountMinOrders()) {
                return ResponseEntity.badRequest()
                        .body("You need at least " + policy.getDiscountMinOrders() + " bookings to qualify for a discount.");
            }

            long totalDiscountUses = bookingRepository.countByPromoAppliedTrue();
            if (totalDiscountUses >= policy.getDiscountMaxUses()) {
                return ResponseEntity.badRequest().body("Discount limit has been reached.");
            }

            if (policy.getDiscountType() == DiscountType.PERCENTAGE) {
                validatedDiscount = Double.parseDouble(
                        String.format("%.2f", request.getBasePrice() * policy.getDiscountValue() / 100)
                );
            } else if (policy.getDiscountType() == DiscountType.FIXED) {
                validatedDiscount = Math.min(policy.getDiscountValue(), request.getBasePrice());
            }
        }

        double validatedTotal = request.getBasePrice() - validatedDiscount;

        car.setRentalStatus(RentalStatus.RENTED);
        carRepository.save(car);

        Booking booking = new Booking();
        booking.setBookingRef(request.getBookingRef());
        booking.setBookingTime(LocalDateTime.now());
        booking.setEndTime(LocalDateTime.now().plusHours(request.getTotalHours()));
        booking.setUser(user);
        booking.setCar(car);
        booking.setDays(request.getDays());
        booking.setHours(request.getHours());
        booking.setTotalHours(request.getTotalHours());
        booking.setHouseNo(request.getHouseNo());
        booking.setRoadNo(request.getRoadNo());
        booking.setBlockNo(request.getBlockNo());
        booking.setServiceCity(request.getServiceCity());
        booking.setPhone(request.getPhone());
        booking.setPaymentMethod(request.getPaymentMethod());
        booking.setPromoApplied(Boolean.TRUE.equals(request.getPromoApplied()) && validatedDiscount > 0);
        booking.setBasePrice(request.getBasePrice());
        booking.setDiscountAmount(validatedDiscount);   // backend calculated
        booking.setTotalPrice(validatedTotal);          // backend calculated
        booking.setPaymentStatus(PaymentStatus.PAID);
        booking.setBookingStatus(BookingStatus.BOOKED);

        bookingRepository.save(booking);

        return ResponseEntity.ok("Booking created successfully"); // 👈 plain string avoids JSON crash
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBookingById(@PathVariable Long id) {
        Optional<Booking> booking = bookingRepository.findById(id);
        return booking.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Booking>> getBookingsByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(bookingRepository.findByUserUserId(userId));
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<?> updatePaymentStatus(@PathVariable Long id,
                                                 @RequestBody java.util.Map<String, String> body) {
        Optional<Booking> optional = bookingRepository.findById(id);
        if (optional.isEmpty()) return ResponseEntity.notFound().build();

        Booking booking = optional.get();
        booking.setPaymentStatus(PaymentStatus.valueOf(body.get("paymentStatus")));
        bookingRepository.save(booking);
        return ResponseEntity.ok("Status updated");
    }
}