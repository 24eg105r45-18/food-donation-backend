
package app.service;

import app.model.FoodDonation;
import app.repository.FoodDonationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodDonationService {

    private final FoodDonationRepository repository;

    public FoodDonationService(FoodDonationRepository repository) {
        this.repository = repository;
    }

    public FoodDonation saveDonation(FoodDonation donation) {
        return repository.save(donation);
    }

    public List<FoodDonation> getAllDonations() {
        return repository.findAll();
    }
}
