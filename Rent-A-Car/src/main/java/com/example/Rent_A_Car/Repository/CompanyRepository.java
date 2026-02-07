package com.example.Rent_A_Car.Repository;

import com.example.Rent_A_Car.Model.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {

         boolean existsByCompanyName(String companyName);

}
