package kg.mega.rentserviceproject.repositories.order;

import kg.mega.rentserviceproject.models.car.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PriceRepo extends JpaRepository<Price, Long> {
    Optional<Price> findByCarId(Long carId);
}
