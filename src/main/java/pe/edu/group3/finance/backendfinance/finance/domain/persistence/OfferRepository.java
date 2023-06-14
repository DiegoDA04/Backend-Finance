package pe.edu.group3.finance.backendfinance.finance.domain.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.group3.finance.backendfinance.finance.domain.model.entity.Offer;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Long> {
    Offer findByTitle(String title);
    Boolean existsByTitle(String title);
}
