package kg.mega.rentserviceproject.services.order.impl;

import kg.mega.rentserviceproject.exceptions.order.OrderDateException;
import kg.mega.rentserviceproject.exceptions.order.OrderNotFoundException;
import kg.mega.rentserviceproject.models.car.Car;
import kg.mega.rentserviceproject.models.order.Order;
import kg.mega.rentserviceproject.repositories.car.CarRepo;
import kg.mega.rentserviceproject.repositories.order.OrderRepo;
import kg.mega.rentserviceproject.services.order.CompleteOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class CompleteOrderServiceImpl implements CompleteOrderService {
    private final OrderRepo orderRepo;
    private final CarRepo carRepo;

    @Override
    public void completeOrder(Long orderId) {
        Order order = orderRepo.getReferenceById(orderId);

        if (order.getId() == null) {
            throw new OrderNotFoundException("Invalid order id.");
        }

        Car car = order.getCar();

        LocalDate
            nowDate = LocalDate.now(),
            endOrderDate = order.getToDate();

        if (nowDate.isAfter(endOrderDate) || nowDate.isEqual(endOrderDate)) {
            car.setRent(false);
            carRepo.save(car);
        } else {
            throw new OrderDateException("The lease hasn't expired yet.");
        }
    }
}
