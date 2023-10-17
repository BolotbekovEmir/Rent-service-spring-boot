package kg.mega.rentserviceproject.repositories.order;

import kg.mega.rentserviceproject.models.order.Discount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscountRepo extends JpaRepository<Discount, Long> {
}
