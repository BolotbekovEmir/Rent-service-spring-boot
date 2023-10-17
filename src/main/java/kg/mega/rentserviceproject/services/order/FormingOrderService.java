package kg.mega.rentserviceproject.services.order;

import kg.mega.rentserviceproject.dto.order.FormingOrderDTO;
import kg.mega.rentserviceproject.dto.order.GetOrderDTO;
import kg.mega.rentserviceproject.models.order.Order;

public interface FormingOrderService {
    GetOrderDTO formingOrder(FormingOrderDTO formingOrderDTO);
}
