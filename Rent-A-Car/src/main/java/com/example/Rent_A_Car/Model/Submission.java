package com.example.Rent_A_Car.Model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "submissions")
public class Submission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "submission_id")
    private Long submissionId;

    @ManyToOne
    @JoinColumn(name = "booking_id", nullable = false)
    private Booking booking;

    @Column(name = "submitted_time", nullable = false)
    private LocalDateTime submittedTime;

    @Column(name = "otp_code")
    private String otpCode;   // can be null for now

    @Column(name = "late_fine", nullable = false)
    private Double lateFine;

    @Column(name = "final_rating", nullable = false)
    private Double finalRating;


    public Submission() {}

    public Submission(Booking booking, LocalDateTime submittedTime,
                      String otpCode, Double lateFine, Double finalRating) {
        this.booking = booking;
        this.submittedTime = submittedTime;
        this.otpCode = otpCode;
        this.lateFine = lateFine;
        this.finalRating = finalRating;
    }


    public Long getSubmissionId() {
        return submissionId;
    }

    public void setSubmissionId(Long submissionId) {
        this.submissionId = submissionId;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public LocalDateTime getSubmittedTime() {
        return submittedTime;
    }

    public void setSubmittedTime(LocalDateTime submittedTime) {
        this.submittedTime = submittedTime;
    }

    public String getOtpCode() {
        return otpCode;
    }

    public void setOtpCode(String otpCode) {
        this.otpCode = otpCode;
    }

    public Double getLateFine() {
        return lateFine;
    }

    public void setLateFine(Double lateFine) {
        this.lateFine = lateFine;
    }

    public Double getFinalRating() {
        return finalRating;
    }

    public void setFinalRating(Double finalRating) {
        this.finalRating = finalRating;
    }
}

