package com.example.Rent_A_Car.Repository;

import com.example.Rent_A_Car.Model.Car;
import com.example.Rent_A_Car.Model.RentalStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface CarRepository extends JpaRepository<Car,Long> {

    List<Car> findByCompanyCompanyId(Long companyId);

    List<Car> findByRentalStatus(RentalStatus status);

    List<Car> findByPricePerHourLessThanEqual(Double maxPrice);

}
