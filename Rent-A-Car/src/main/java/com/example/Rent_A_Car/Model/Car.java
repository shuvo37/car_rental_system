package com.example.Rent_A_Car.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "car_id")
    private Long carId;

    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    @Column(name = "model_name", nullable = false)
    private String modelName;

    @Column(name = "price_per_hour", nullable = false)
    private Double pricePerHour;

    @Enumerated(EnumType.STRING)
    @Column(name = "rental_status", nullable = false)
    private RentalStatus rentalStatus;

    @Column(name = "seats", nullable = false)
    private Integer seats;

    @Enumerated(EnumType.STRING)
    @Column(name = "fuel", nullable = false)
    private FuelType fuel;

    @Lob
    @Column(name = "image", columnDefinition = "LONGBLOB")
    private byte[] image;

    @Column(name = "tag", nullable = true)
    private String tag;

    // 🔹 Constructors
    public Car() {}

    public Car(Company company, String modelName, Double pricePerHour, RentalStatus rentalStatus,
               Integer seats, FuelType fuel, byte[] image, String tag) {
        this.company = company;
        this.modelName = modelName;
        this.pricePerHour = pricePerHour;
        this.rentalStatus = rentalStatus;
        this.seats = seats;
        this.fuel = fuel;
        this.image = image;
        this.tag = tag;
    }

    // 🔹 Getters and Setters
    public Long getCarId() { return carId; }
    public void setCarId(Long carId) { this.carId = carId; }

    public Company getCompany() { return company; }
    public void setCompany(Company company) { this.company = company; }

    public String getModelName() { return modelName; }
    public void setModelName(String modelName) { this.modelName = modelName; }

    public Double getPricePerHour() { return pricePerHour; }
    public void setPricePerHour(Double pricePerHour) { this.pricePerHour = pricePerHour; }

    public RentalStatus getRentalStatus() { return rentalStatus; }
    public void setRentalStatus(RentalStatus rentalStatus) { this.rentalStatus = rentalStatus; }

    public Integer getSeats() { return seats; }
    public void setSeats(Integer seats) { this.seats = seats; }

    public FuelType getFuel() { return fuel; }
    public void setFuel(FuelType fuel) { this.fuel = fuel; }

    public byte[] getImage() { return image; }
    public void setImage(byte[] image) { this.image = image; }

    public String getTag() { return tag; }
    public void setTag(String tag) { this.tag = tag; }
}