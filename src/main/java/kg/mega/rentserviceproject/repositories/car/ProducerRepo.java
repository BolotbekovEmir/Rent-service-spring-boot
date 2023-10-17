package kg.mega.rentserviceproject.repositories.car;

import kg.mega.rentserviceproject.models.car.Producer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProducerRepo extends JpaRepository<Producer, Long> {
    Optional<Producer> findByName(String name);
}
