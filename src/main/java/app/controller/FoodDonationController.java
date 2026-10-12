
package app.controller;

import app.model.FoodDonation;
import app.service.FoodDonationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/donations")
@CrossOrigin(origins = {
        "http://localhost:5173",
        "https://24eg105r45.netlify.app"
})
public class FoodDonationController {

    private final FoodDonationService service;

    public FoodDonationController(FoodDonationService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<FoodDonation> createDonation(
            @RequestBody FoodDonation donation) {
        FoodDonation saved = service.saveDonation(donation);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping
    public List<FoodDonation> getAllDonations() {
        return service.getAllDonations();
    }
}
