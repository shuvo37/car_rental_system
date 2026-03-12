package com.example.Rent_A_Car.DTO;

import com.example.Rent_A_Car.Model.PaymentMethod;

public class BookingRequest {

    private Long    userId;
    private Long    carId;
    private String  bookingRef;

    private Integer days;
    private Integer hours;
    private Integer totalHours;

    private String houseNo;
    private String roadNo;
    private String blockNo;
    private String serviceCity;
    private String phone;

    private PaymentMethod paymentMethod;

    private Boolean promoApplied;
    private Double  basePrice;
    private Double  discountAmount;
    private Double  totalPrice;

    public Long getUserId()                       { return userId; }
    public void setUserId(Long userId)            { this.userId = userId; }

    public Long getCarId()                        { return carId; }
    public void setCarId(Long carId)              { this.carId = carId; }

    public String getBookingRef()                 { return bookingRef; }
    public void setBookingRef(String ref)         { this.bookingRef = ref; }

    public Integer getDays()                      { return days; }
    public void setDays(Integer days)             { this.days = days; }

    public Integer getHours()                     { return hours; }
    public void setHours(Integer hours)           { this.hours = hours; }

    public Integer getTotalHours()                { return totalHours; }
    public void setTotalHours(Integer totalHours) { this.totalHours = totalHours; }

    public String getHouseNo()                    { return houseNo; }
    public void setHouseNo(String houseNo)        { this.houseNo = houseNo; }

    public String getRoadNo()                     { return roadNo; }
    public void setRoadNo(String roadNo)          { this.roadNo = roadNo; }

    public String getBlockNo()                    { return blockNo; }
    public void setBlockNo(String blockNo)        { this.blockNo = blockNo; }

    public String getServiceCity()                { return serviceCity; }
    public void setServiceCity(String city)       { this.serviceCity = city; }

    public String getPhone()                      { return phone; }
    public void setPhone(String phone)            { this.phone = phone; }

    public PaymentMethod getPaymentMethod()       { return paymentMethod; }
    public void setPaymentMethod(PaymentMethod m) { this.paymentMethod = m; }

    public Boolean getPromoApplied()              { return promoApplied; }
    public void setPromoApplied(Boolean applied)  { this.promoApplied = applied; }

    public Double getBasePrice()                  { return basePrice; }
    public void setBasePrice(Double basePrice)    { this.basePrice = basePrice; }

    public Double getDiscountAmount()             { return discountAmount; }
    public void setDiscountAmount(Double d)       { this.discountAmount = d; }

    public Double getTotalPrice()                 { return totalPrice; }
    public void setTotalPrice(Double totalPrice)  { this.totalPrice = totalPrice; }
}