package com.example.Rent_A_Car.Repository;

import com.example.Rent_A_Car.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {


    Optional<User> findByEmail(String email);



}
