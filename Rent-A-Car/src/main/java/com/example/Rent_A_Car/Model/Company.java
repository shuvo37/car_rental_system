package com.example.Rent_A_Car.Model;

import jakarta.persistence.*;
import java.util.ArrayList;   // 👈 import 1
import java.util.List;        // 👈 import 2

@Entity
@Table(name = "companies")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "company_id")
    private Long companyId;

    @Column(name = "company_name", nullable = false, unique = true)
    private String companyName;

    @Column(name = "company_email", nullable = false, unique = true)
    private String companyEmail;

    @Column(name = "company_phone_number", nullable = false)
    private String companyPhoneNumber;

    @Column(name = "company_country", nullable = false)
    private String companyCountry;

    @Column(name = "company_city", nullable = false)
    private String companyCity;

    @Column(name = "company_address", nullable = false)
    private String companyAddress;

    // 👇 import 3 — this is the fix
    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Car> cars = new ArrayList<>();

    // 🔹 Constructors
    public Company() {
    }

    public Company(String companyName, String companyEmail, String companyPhoneNumber,
                   String companyCountry, String companyCity, String companyAddress) {
        this.companyName = companyName;
        this.companyEmail = companyEmail;
        this.companyPhoneNumber = companyPhoneNumber;
        this.companyCountry = companyCountry;
        this.companyCity = companyCity;
        this.companyAddress = companyAddress;
    }

    // 🔹 Getters and Setters
    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyEmail() {
        return companyEmail;
    }

    public void setCompanyEmail(String companyEmail) {
        this.companyEmail = companyEmail;
    }

    public String getCompanyPhoneNumber() {
        return companyPhoneNumber;
    }

    public void setCompanyPhoneNumber(String companyPhoneNumber) {
        this.companyPhoneNumber = companyPhoneNumber;
    }

    public String getCompanyCountry() {
        return companyCountry;
    }

    public void setCompanyCountry(String companyCountry) {
        this.companyCountry = companyCountry;
    }

    public String getCompanyCity() {
        return companyCity;
    }

    public void setCompanyCity(String companyCity) {
        this.companyCity = companyCity;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    // 👇 Getter and Setter for cars
    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }
}