package com.example.Rent_A_Car.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "user_ratings")
public class UserRating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rating_id")
    private Long ratingId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "rating", nullable = false)
    private Double rating;

    @Column(name = "discount_percentage", nullable = false)
    private Double discountPercentage;

    // 🔹 Constructors
    public UserRating() {}

    public UserRating(User user, Double rating, Double discountPercentage) {
        this.user = user;
        this.rating = rating;
        this.discountPercentage = discountPercentage;
    }

    // 🔹 Getters & Setters
    public Long getRatingId() {
        return ratingId;
    }

    public void setRatingId(Long ratingId) {
        this.ratingId = ratingId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Double getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(Double discountPercentage) {
        this.discountPercentage = discountPercentage;
    }
}