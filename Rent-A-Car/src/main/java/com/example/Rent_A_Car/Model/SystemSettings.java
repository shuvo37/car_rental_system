package com.example.Rent_A_Car.Model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "system_settings")
public class SystemSettings {

    @Id
    private Long id;   // always 1

    @Column(name = "rating_per_booking")
    private Integer ratingPerBooking;

    @Column(name = "late_penalty_rating")
    private Integer latePenaltyRating;

    @Column(name = "discount_per_rating")
    private Integer discountPerRating;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // Getters & Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getRatingPerBooking() {
        return ratingPerBooking;
    }

    public void setRatingPerBooking(Integer ratingPerBooking) {
        this.ratingPerBooking = ratingPerBooking;
    }

    public Integer getLatePenaltyRating() {
        return latePenaltyRating;
    }

    public void setLatePenaltyRating(Integer latePenaltyRating) {
        this.latePenaltyRating = latePenaltyRating;
    }

    public Integer getDiscountPerRating() {
        return discountPerRating;
    }

    public void setDiscountPerRating(Integer discountPerRating) {
        this.discountPerRating = discountPerRating;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
