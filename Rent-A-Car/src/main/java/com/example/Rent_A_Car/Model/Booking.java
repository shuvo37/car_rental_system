package com.example.Rent_A_Car.Model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "bookings")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "booking_id")
    private Long bookingId;

    @Column(name = "booking_ref", nullable = false, unique = true)
    private String bookingRef;

    @Column(name = "booking_time", nullable = false)
    private LocalDateTime bookingTime;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "car_id", nullable = false)
    private Car car;

    @Column(name = "days", nullable = false)
    private Integer days;

    @Column(name = "hours", nullable = false)
    private Integer hours;

    @Column(name = "total_hours", nullable = false)
    private Integer totalHours;

    @Column(name = "house_no", nullable = false)
    private String houseNo;

    @Column(name = "road_no", nullable = false)
    private String roadNo;

    @Column(name = "block_no", nullable = false)
    private String blockNo;

    @Column(name = "service_city", nullable = false)
    private String serviceCity;

    @Column(name = "phone", nullable = false)
    private String phone;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_method", nullable = false)
    private PaymentMethod paymentMethod;

    @Column(name = "promo_applied", nullable = false)
    private Boolean promoApplied = false;

    @Column(name = "base_price", nullable = false)
    private Double basePrice;

    @Column(name = "discount_amount", nullable = false)
    private Double discountAmount = 0.0;   // how much was discounted in $

    @Column(name = "total_price", nullable = false)
    private Double totalPrice;

    @Column(name = "end_time", nullable = false)
    private LocalDateTime endTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_status", nullable = false)
    private PaymentStatus paymentStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "booking_status", nullable = false)
    private BookingStatus bookingStatus = BookingStatus.BOOKED;

    public Booking() {}

    public Long getBookingId()                       { return bookingId; }
    public void setBookingId(Long id)                { this.bookingId = id; }

    public String getBookingRef()                    { return bookingRef; }
    public void setBookingRef(String ref)            { this.bookingRef = ref; }

    public LocalDateTime getBookingTime()            { return bookingTime; }
    public void setBookingTime(LocalDateTime time)   { this.bookingTime = time; }

    public User getUser()                            { return user; }
    public void setUser(User user)                   { this.user = user; }

    public Car getCar()                              { return car; }
    public void setCar(Car car)                      { this.car = car; }

    public Integer getDays()                         { return days; }
    public void setDays(Integer days)                { this.days = days; }

    public Integer getHours()                        { return hours; }
    public void setHours(Integer hours)              { this.hours = hours; }

    public Integer getTotalHours()                   { return totalHours; }
    public void setTotalHours(Integer totalHours)    { this.totalHours = totalHours; }

    public String getHouseNo()                       { return houseNo; }
    public void setHouseNo(String houseNo)           { this.houseNo = houseNo; }

    public String getRoadNo()                        { return roadNo; }
    public void setRoadNo(String roadNo)             { this.roadNo = roadNo; }

    public String getBlockNo()                       { return blockNo; }
    public void setBlockNo(String blockNo)           { this.blockNo = blockNo; }

    public String getServiceCity()                   { return serviceCity; }
    public void setServiceCity(String serviceCity)   { this.serviceCity = serviceCity; }

    public String getPhone()                         { return phone; }
    public void setPhone(String phone)               { this.phone = phone; }

    public PaymentMethod getPaymentMethod()          { return paymentMethod; }
    public void setPaymentMethod(PaymentMethod m)    { this.paymentMethod = m; }

    public Boolean getPromoApplied()                 { return promoApplied; }
    public void setPromoApplied(Boolean applied)     { this.promoApplied = applied; }

    public Double getBasePrice()                     { return basePrice; }
    public void setBasePrice(Double basePrice)       { this.basePrice = basePrice; }

    public Double getDiscountAmount()                { return discountAmount; }
    public void setDiscountAmount(Double discount)   { this.discountAmount = discount; }

    public Double getTotalPrice()                    { return totalPrice; }
    public void setTotalPrice(Double totalPrice)     { this.totalPrice = totalPrice; }

    public LocalDateTime getEndTime()              { return endTime; }
    public void setEndTime(LocalDateTime endTime)  { this.endTime = endTime; }

    public PaymentStatus getPaymentStatus()          { return paymentStatus; }
    public void setPaymentStatus(PaymentStatus s)    { this.paymentStatus = s; }

    public BookingStatus getBookingStatus()          { return bookingStatus; }
    public void setBookingStatus(BookingStatus s)    { this.bookingStatus = s; }
}