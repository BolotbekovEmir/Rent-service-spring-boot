package kg.mega.rentserviceproject.services.order.impl;

import kg.mega.rentserviceproject.dto.order.GetOrderDTO;
import kg.mega.rentserviceproject.models.order.Order;
import kg.mega.rentserviceproject.services.car.GetCarCardService;
import kg.mega.rentserviceproject.services.order.GetOrderService;
import kg.mega.rentserviceproject.services.user.GetProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetOrderServiceImpl implements GetOrderService {
    private final GetProfileService getProfileService;
    private final GetCarCardService getCarCardService;

    @Override
    public GetOrderDTO getOrder(Order order, String sessionId) {
        return new GetOrderDTO(
                getProfileService.getProfile(sessionId),
                getCarCardService.getCarCard(order.getCar().getId()),
                order.getDiscount().getDiscountPercentage(),
                order.getFromDate(),
                order.getToDate(),
                order.getCountDays(),
                order.getTotalAmount()
        );
    }
}
