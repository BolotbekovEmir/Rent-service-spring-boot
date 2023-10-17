package kg.mega.rentserviceproject.repositories.car;

import kg.mega.rentserviceproject.models.car.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ModelRepo extends JpaRepository<Model, Long> {
    Optional<Model> findByName(String name);
}
