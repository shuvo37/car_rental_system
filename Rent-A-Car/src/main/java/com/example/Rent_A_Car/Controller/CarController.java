package com.example.Rent_A_Car.Controller;

import com.example.Rent_A_Car.Model.Car;
import com.example.Rent_A_Car.Model.RentalStatus;
import com.example.Rent_A_Car.Repository.CarRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cars")
public class CarController {

    private final CarRepository carRepository;

    public CarController(CarRepository carRepository) {
        this.carRepository = carRepository;
    }


    @PostMapping
    public ResponseEntity<?> createCar(@RequestBody Car car) {
        return ResponseEntity.ok(carRepository.save(car));
    }


    @GetMapping
    public ResponseEntity<List<Car>> getAllCars(
            @RequestParam(required = false) Long companyId,
            @RequestParam(required = false) RentalStatus status
    ) {

        if (companyId != null) {
            return ResponseEntity.ok(carRepository.findByCompanyCompanyId(companyId));
        }

        if (status != null) {
            return ResponseEntity.ok(carRepository.findByRentalStatus(status));
        }

        return ResponseEntity.ok(carRepository.findAll());
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getCarById(@PathVariable Long id) {
        Optional<Car> car = carRepository.findById(id);
        return car.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @GetMapping("/by-price")
    public ResponseEntity<List<Car>> getCarsBelowPrice(@RequestParam Double maxPrice) {
        return ResponseEntity.ok(carRepository.findByPricePerHourLessThanEqual(maxPrice));
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> updateCar(@PathVariable Long id,
                                       @RequestBody Car updatedCar) {

        Optional<Car> optionalCar = carRepository.findById(id);

        if (optionalCar.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Car car = optionalCar.get();
        car.setModelName(updatedCar.getModelName());
        car.setPricePerHour(updatedCar.getPricePerHour());
        car.setRentalStatus(updatedCar.getRentalStatus());
        car.setCompany(updatedCar.getCompany());

        return ResponseEntity.ok(carRepository.save(car));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCar(@PathVariable Long id) {

        if (!carRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        carRepository.deleteById(id);
        return ResponseEntity.ok("Car deleted successfully");
    }
}