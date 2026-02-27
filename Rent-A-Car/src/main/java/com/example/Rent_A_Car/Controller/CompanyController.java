package com.example.Rent_A_Car.Controller;

import com.example.Rent_A_Car.Model.Company;
import com.example.Rent_A_Car.Repository.CompanyRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/companies")
public class CompanyController {

    private final CompanyRepository companyRepository;

    public CompanyController(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }


    @PostMapping
    public ResponseEntity<?> createCompany(@RequestBody Company company) {

        if (company.getCompanyName() == null || company.getCompanyName().isEmpty()) {
            return ResponseEntity.badRequest().body("Company name is required");
        }

        if (company.getCompanyEmail() == null || company.getCompanyEmail().isEmpty()) {
            return ResponseEntity.badRequest().body("Company email is required");
        }

        if (company.getCompanyPhoneNumber() == null || company.getCompanyPhoneNumber().isEmpty()) {
            return ResponseEntity.badRequest().body("Company phone number is required");
        }

        if (company.getCompanyCountry() == null || company.getCompanyCountry().isEmpty()) {
            return ResponseEntity.badRequest().body("Company country is required");
        }

        if (company.getCompanyCity() == null || company.getCompanyCity().isEmpty()) {
            return ResponseEntity.badRequest().body("Company city is required");
        }

        if (company.getCompanyAddress() == null || company.getCompanyAddress().isEmpty()) {
            return ResponseEntity.badRequest().body("Company address is required");
        }

        if (companyRepository.existsByCompanyName(company.getCompanyName())) {
            return ResponseEntity.badRequest().body("Company name already exists");
        }

        if (companyRepository.existsByCompanyEmail(company.getCompanyEmail())) {
            return ResponseEntity.badRequest().body("Company email already exists");
        }

        Company savedCompany = companyRepository.save(company);
        return ResponseEntity.ok(savedCompany);
    }


    @GetMapping
    public ResponseEntity<List<Company>> getAllCompanies() {
        List<Company> companies = companyRepository.findAll();
        return ResponseEntity.ok(companies);
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getCompanyById(@PathVariable Long id) {
        Optional<Company> company = companyRepository.findById(id);

        if (company.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(company.get());
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> updateCompany(@PathVariable Long id,
                                           @RequestBody Company updatedCompany) {

        Optional<Company> optionalCompany = companyRepository.findById(id);

        if (optionalCompany.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Company company = optionalCompany.get();

        // 🔹 Update all fields
        company.setCompanyName(updatedCompany.getCompanyName());
        company.setCompanyEmail(updatedCompany.getCompanyEmail());
        company.setCompanyPhoneNumber(updatedCompany.getCompanyPhoneNumber());
        company.setCompanyCountry(updatedCompany.getCompanyCountry());
        company.setCompanyCity(updatedCompany.getCompanyCity());
        company.setCompanyAddress(updatedCompany.getCompanyAddress());

        Company saved = companyRepository.save(company);
        return ResponseEntity.ok(saved);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCompany(@PathVariable Long id) {

        if (!companyRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        companyRepository.deleteById(id);
        return ResponseEntity.ok("Company deleted successfully");
    }
}