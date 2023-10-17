package kg.mega.rentserviceproject.services.order;

import kg.mega.rentserviceproject.models.order.Order;

public interface SendMessageService {
    void sendOrderToEmail(Order order);
}
