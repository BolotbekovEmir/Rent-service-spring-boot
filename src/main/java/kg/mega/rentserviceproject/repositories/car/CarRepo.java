package kg.mega.rentserviceproject.repositories.car;

import kg.mega.rentserviceproject.dto.car.CarCardDTO;
import kg.mega.rentserviceproject.models.car.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarRepo extends JpaRepository<Car, Long> {
    Optional<Car> findByVin(String vin);

    @Query("""
    select new kg.mega.rentserviceproject.dto.car.CarCardDTO(c.id, m.name, pc.name, c.yearProduction, p.dayPrice, m.bodyType, m.modelClass, c.isRent, max(o.toDate))
    from Car c
    left join Price p on c.id = p.car.id
    join Model m on c.model.id = m.id
    join Producer pc on m.producer.id = pc.id
    left join Order o on c.id = o.car.id
    group by c.id, m.name, pc.name, c.yearProduction, p.dayPrice, m.bodyType, m.modelClass, c.isRent
    """)
    List<CarCardDTO> findAllCarsCards();

    @Query("""
    select new kg.mega.rentserviceproject.dto.car.CarCardDTO(c.id, m.name, pc.name, c.yearProduction, p.dayPrice, m.bodyType, m.modelClass, c.isRent, max(o.toDate))
    from Car c
    left join Price p on c.id = p.car.id
    join Model m on c.model.id = m.id
    join Producer pc on m.producer.id = pc.id
    left join Order o on c.id = o.car.id
    where c.id = :carId
    group by c.id, m.name, pc.name, c.yearProduction, p.dayPrice, m.bodyType, m.modelClass, c.isRent
    """)
    Optional<CarCardDTO> findCarById(@Param("carId") Long carId);
}
