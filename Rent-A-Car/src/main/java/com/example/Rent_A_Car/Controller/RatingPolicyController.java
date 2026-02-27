package com.example.Rent_A_Car.Controller;

import com.example.Rent_A_Car.Model.RatingPolicy;
import com.example.Rent_A_Car.Repository.RatingPolicyRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/policy")
public class RatingPolicyController {

    private final RatingPolicyRepository policyRepository;

    public RatingPolicyController(RatingPolicyRepository policyRepository) {
        this.policyRepository = policyRepository;
    }

    // 👇 Get current policy
    @GetMapping
    public ResponseEntity<?> getPolicy() {
        return ResponseEntity.ok(
                policyRepository.findById(1L).orElseGet(() -> {
                    RatingPolicy defaultPolicy = new RatingPolicy();
                    return policyRepository.save(defaultPolicy);  // 👈 auto create if not exists
                })
        );
    }

    // 👇 Update policy
    @PutMapping
    public ResponseEntity<?> updatePolicy(@RequestBody RatingPolicy updatedPolicy) {
        RatingPolicy policy = policyRepository.findById(1L).orElseGet(RatingPolicy::new);

        policy.setPolicyId(1L);
        policy.setRating(updatedPolicy.getRating());
        policy.setLatePenalty(updatedPolicy.getLatePenalty());
        policy.setDiscountEnabled(updatedPolicy.getDiscountEnabled());
        policy.setDiscountType(updatedPolicy.getDiscountType());
        policy.setDiscountValue(updatedPolicy.getDiscountValue());
        policy.setDiscountMinOrders(updatedPolicy.getDiscountMinOrders());
        policy.setDiscountMaxUses(updatedPolicy.getDiscountMaxUses());

        return ResponseEntity.ok(policyRepository.save(policy));
    }
}