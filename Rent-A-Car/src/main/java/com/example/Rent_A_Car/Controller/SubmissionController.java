package com.example.Rent_A_Car.Controller;
import com.example.Rent_A_Car.Model.Submission;
import com.example.Rent_A_Car.Repository.SubmissionRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("/api/submissions")
public class SubmissionController {

    private final SubmissionRepository submissionRepository;

    public SubmissionController(SubmissionRepository submissionRepository) {
        this.submissionRepository = submissionRepository;
    }


    @PostMapping
    public ResponseEntity<?> createSubmission(@RequestBody Submission submission) {

        submission.setSubmittedTime(LocalDateTime.now());
        return ResponseEntity.ok(submissionRepository.save(submission));
    }


    @GetMapping("/booking/{bookingId}")
    public ResponseEntity<?> getSubmissionByBooking(@PathVariable Long bookingId) {
        Optional<Submission> submission = submissionRepository.findByBookingBookingId(bookingId);
        return submission
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> updateSubmission(@PathVariable Long id,
                                              @RequestBody Submission updatedSubmission) {

        Optional<Submission> optionalSubmission = submissionRepository.findById(id);

        if (optionalSubmission.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Submission submission = optionalSubmission.get();
        submission.setOtpCode(updatedSubmission.getOtpCode());
        submission.setLateFine(updatedSubmission.getLateFine());
        submission.setFinalRating(updatedSubmission.getFinalRating());

        return ResponseEntity.ok(submissionRepository.save(submission));
    }
}