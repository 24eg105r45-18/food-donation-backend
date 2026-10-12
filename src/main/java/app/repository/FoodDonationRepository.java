
package app.repository;

import app.model.FoodDonation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodDonationRepository
        extends JpaRepository<FoodDonation, Long> {
}
