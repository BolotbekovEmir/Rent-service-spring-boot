package kg.mega.rentserviceproject.repositories.order;

import kg.mega.rentserviceproject.models.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepo extends JpaRepository<Order, Long> {}
