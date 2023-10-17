package kg.mega.rentserviceproject.services.order;

import kg.mega.rentserviceproject.dto.order.GetOrderDTO;
import kg.mega.rentserviceproject.models.order.Order;

public interface GetOrderService {
    GetOrderDTO getOrder(Order order, String sessionId);
}
