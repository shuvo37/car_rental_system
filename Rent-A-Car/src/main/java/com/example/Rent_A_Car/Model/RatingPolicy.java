package com.example.Rent_A_Car.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "rating_policy")
public class RatingPolicy {

    @Id
    @Column(name = "policy_id")
    private Long policyId = 1L;  // 👈 always 1, single row

    @Column(name = "rating", nullable = false)
    private Double rating = 4.5;

    @Column(name = "late_penalty", nullable = false)
    private Double latePenalty = 15.0;

    @Column(name = "discount_enabled", nullable = false)
    private Boolean discountEnabled = true;

    @Enumerated(EnumType.STRING)
    @Column(name = "discount_type", nullable = false)
    private DiscountType discountType = DiscountType.PERCENTAGE;

    @Column(name = "discount_value", nullable = false)
    private Double discountValue = 10.0;

    @Column(name = "discount_min_orders", nullable = false)
    private Integer discountMinOrders = 5;

    @Column(name = "discount_max_uses", nullable = false)
    private Integer discountMaxUses = 100;

    // 🔹 Constructors
    public RatingPolicy() {}

    // 🔹 Getters & Setters
    public Long getPolicyId() { return policyId; }
    public void setPolicyId(Long policyId) { this.policyId = policyId; }

    public Double getRating() { return rating; }
    public void setRating(Double rating) { this.rating = rating; }

    public Double getLatePenalty() { return latePenalty; }
    public void setLatePenalty(Double latePenalty) { this.latePenalty = latePenalty; }

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