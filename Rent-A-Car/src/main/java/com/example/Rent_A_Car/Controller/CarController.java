package com.example.Rent_A_Car.Controller;

import com.example.Rent_A_Car.Model.Car;
import com.example.Rent_A_Car.Model.Company;
import com.example.Rent_A_Car.Model.FuelType;
import com.example.Rent_A_Car.Model.RentalStatus;
import com.example.Rent_A_Car.Repository.CarRepository;
import com.example.Rent_A_Car.Repository.CompanyRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/cars")
public class CarController {

    private final CarRepository carRepository;
    private final CompanyRepository companyRepository; // 👈 added

    public CarController(CarRepository carRepository, CompanyRepository companyRepository) {
        this.carRepository = carRepository;
        this.companyRepository = companyRepository;    // 👈 added
    }

    @PutMapping(value = "/{id}", consumes = "multipart/form-data")
    public ResponseEntity<?> updateCar(
            @PathVariable Long id,
            @RequestParam("modelName")    String modelName,
            @RequestParam("pricePerHour") Double pricePerHour,
            @RequestParam("rentalStatus") String rentalStatus,
            @RequestParam("seats")        Integer seats,
            @RequestParam("fuel")         String fuel,
            @RequestParam(value = "tag",   required = false) String tag,
            @RequestParam("companyId")    Long companyId,
            @RequestParam(value = "image", required = false) MultipartFile image  // 👈 optional — keep existing if not sent
    ) throws IOException {

        Optional<Car> optionalCar = carRepository.findById(id);
        if (optionalCar.isEmpty()) return ResponseEntity.notFound().build();

        Company company = companyRepository.findById(companyId).orElse(null);
        if (company == null) return ResponseEntity.badRequest().body("Company not found");

        Car car = optionalCar.get();
        car.setModelName(modelName);
        car.setPricePerHour(pricePerHour);
        car.setRentalStatus(RentalStatus.valueOf(rentalStatus));
        car.setSeats(seats);
        car.setFuel(FuelType.valueOf(fuel));
        car.setTag(tag);
        car.setCompany(company);

        // 👇 only overwrite image if admin uploaded a new one
        if (image != null && !image.isEmpty()) {
            car.setImage(image.getBytes());
        }

        return ResponseEntity.ok(carRepository.save(car));
    }


    @GetMapping
    public ResponseEntity<List<Car>> getAllCars(
            @RequestParam(required = false) Long companyId,
            @RequestParam(required = false) RentalStatus status
    ) {
        if (companyId != null) return ResponseEntity.ok(carRepository.findByCompanyCompanyId(companyId));
        if (status != null)    return ResponseEntity.ok(carRepository.findByRentalStatus(status));
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
    public ResponseEntity<?> updateCar(@PathVariable Long id, @RequestBody Car updatedCar) {
        Optional<Car> optionalCar = carRepository.findById(id);
        if (optionalCar.isEmpty()) return ResponseEntity.notFound().build();

        Car car = optionalCar.get();
        car.setModelName(updatedCar.getModelName());
        car.setPricePerHour(updatedCar.getPricePerHour());
        car.setRentalStatus(updatedCar.getRentalStatus());
        car.setCompany(updatedCar.getCompany());
        car.setSeats(updatedCar.getSeats());       // 👈 added
        car.setFuel(updatedCar.getFuel());         // 👈 added
        car.setTag(updatedCar.getTag());           // 👈 added

        return ResponseEntity.ok(carRepository.save(car));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCar(@PathVariable Long id) {
        if (!carRepository.existsById(id)) return ResponseEntity.notFound().build();
        carRepository.deleteById(id);
        return ResponseEntity.ok("Car deleted successfully");
    }
}