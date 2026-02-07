package com.example.Rent_A_Car.Controller;

import com.example.Rent_A_Car.Model.Booking;
import com.example.Rent_A_Car.Repository.BookingRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    private final BookingRepository bookingRepository;

    public BookingController(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }


    @PostMapping
    public ResponseEntity<?> createBooking(@RequestBody Booking booking) {
        // Later: calculate total_price here
        return ResponseEntity.ok(bookingRepository.save(booking));
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getBookingById(@PathVariable Long id) {
        Optional<Booking> booking = bookingRepository.findById(id);
        return booking
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Booking>> getBookingsByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(bookingRepository.findByUserUserId(userId));
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> updateBooking(@PathVariable Long id,
                                           @RequestBody Booking updatedBooking) {

        Optional<Booking> optionalBooking = bookingRepository.findById(id);

        if (optionalBooking.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Booking booking = optionalBooking.get();
        booking.setStartTime(updatedBooking.getStartTime());
        booking.setEndTime(updatedBooking.getEndTime());
        booking.setTotalPrice(updatedBooking.getTotalPrice());
        booking.setStatus(updatedBooking.getStatus());

        return ResponseEntity.ok(bookingRepository.save(booking));
    }
}