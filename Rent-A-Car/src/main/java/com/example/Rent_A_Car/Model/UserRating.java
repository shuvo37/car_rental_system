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
    private Double rating = 4.5;                    // 👈 default value

    @Column(name = "late_penalty", nullable = false)
    private Double latePenalty = 15.0;              // 👈 added

    @Column(name = "discount_percentage", nullable = false)
    private Double discountPercentage = 10.0;       // 👈 default value

    @Column(name = "discount_enabled", nullable = false)
    private Boolean discountEnabled = true;         // 👈 added

    @Enumerated(EnumType.STRING)
    @Column(name = "discount_type", nullable = false)
    private DiscountType discountType = DiscountType.PERCENTAGE;  // 👈 added

    @Column(name = "discount_value", nullable = false)
    private Double discountValue = 10.0;            // 👈 added

    @Column(name = "discount_min_orders", nullable = false)
    private Integer discountMinOrders = 5;          // 👈 added

    @Column(name = "discount_max_uses", nullable = false)
    private Integer discountMaxUses = 100;          // 👈 added

    // 🔹 Constructors
    public UserRating() {}

    public UserRating(User user, Double rating, Double latePenalty,
                      Double discountPercentage, Boolean discountEnabled,
                      DiscountType discountType, Double discountValue,
                      Integer discountMinOrders, Integer discountMaxUses) {
        this.user = user;
        this.rating = rating;
        this.latePenalty = latePenalty;
        this.discountPercentage = discountPercentage;
        this.discountEnabled = discountEnabled;
        this.discountType = discountType;
        this.discountValue = discountValue;
        this.discountMinOrders = discountMinOrders;
        this.discountMaxUses = discountMaxUses;
    }

    // 🔹 Getters & Setters
    public Long getRatingId() { return ratingId; }
    public void setRatingId(Long ratingId) { this.ratingId = ratingId; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public Double getRating() { return rating; }
    public void setRating(Double rating) { this.rating = rating; }

    public Double getLatePenalty() { return latePenalty; }
    public void setLatePenalty(Double latePenalty) { this.latePenalty = latePenalty; }

    public Double getDiscountPercentage() { return discountPercentage; }
    public void setDiscountPercentage(Double discountPercentage) { this.discountPercentage = discountPercentage; }

    public Boolean getDiscountEnabled() { return discountEnabled; }
    public void setDiscountEnabled(Boolean discountEnabled) { this.discountEnabled = discountEnabled; }

    public DiscountType getDiscountType() { return discountType; }
    public void setDiscountType(DiscountType discountType) { this.discountType = discountType; }

    public Double getDiscountValue() { return discountValue; }
    public void setDiscountValue(Double discountValue) { this.discountValue = discountValue; }

    public Integer getDiscountMinOrders() { return discountMinOrders; }
    public void setDiscountMinOrders(Integer discountMinOrders) { this.discountMinOrders = discountMinOrders; }

    public Integer getDiscountMaxUses() { return discountMaxUses; }
    public void setDiscountMaxUses(Integer discountMaxUses) { this.discountMaxUses = discountMaxUses; }
}