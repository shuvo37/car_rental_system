package com.example.Rent_A_Car.Controller;

import com.example.Rent_A_Car.Model.SystemSettings;
import com.example.Rent_A_Car.Repository.SystemSettingsRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/admin/settings")
public class SystemSettingsController {

    private final SystemSettingsRepository systemSettingsRepository;

    public SystemSettingsController(SystemSettingsRepository systemSettingsRepository) {
        this.systemSettingsRepository = systemSettingsRepository;
    }

    // GET settings
    @GetMapping
    public ResponseEntity<SystemSettings> getSettings() {
        return systemSettingsRepository.findById(1L)
                .map(ResponseEntity::ok)
                .orElseGet(() -> {
                    // create default row if not exists
                    SystemSettings settings = new SystemSettings();
                    settings.setId(1L);
                    settings.setRatingPerBooking(2);
                    settings.setLatePenaltyRating(-1);
                    settings.setDiscountPerRating(1);
                    settings.setUpdatedAt(LocalDateTime.now());

                    SystemSettings saved = systemSettingsRepository.save(settings);
                    return ResponseEntity.ok(saved);
                });
    }

    // UPDATE settings
    @PutMapping
    public ResponseEntity<SystemSettings> updateSettings(@RequestBody SystemSettings newSettings) {

        SystemSettings settings = systemSettingsRepository.findById(1L)
                .orElseGet(() -> {
                    SystemSettings s = new SystemSettings();
                    s.setId(1L);
                    return s;
                });

        settings.setRatingPerBooking(newSettings.getRatingPerBooking());
        settings.setLatePenaltyRating(newSettings.getLatePenaltyRating());
        settings.setDiscountPerRating(newSettings.getDiscountPerRating());
        settings.setUpdatedAt(LocalDateTime.now());

        SystemSettings saved = systemSettingsRepository.save(settings);

        return ResponseEntity.ok(saved);
    }
}
