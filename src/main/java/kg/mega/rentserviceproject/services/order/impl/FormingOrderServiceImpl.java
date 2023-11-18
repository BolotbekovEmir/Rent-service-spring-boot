package kg.mega.rentserviceproject.services.order.impl;

import kg.mega.rentserviceproject.dto.order.FormingOrderDTO;
import kg.mega.rentserviceproject.dto.order.GetOrderDTO;
import kg.mega.rentserviceproject.exceptions.order.PriceNotFoundException;
import kg.mega.rentserviceproject.models.car.Car;
import kg.mega.rentserviceproject.models.order.Discount;
import kg.mega.rentserviceproject.models.order.Order;
import kg.mega.rentserviceproject.models.user.User;
import kg.mega.rentserviceproject.repositories.car.CarRepo;
import kg.mega.rentserviceproject.repositories.order.DiscountRepo;
import kg.mega.rentserviceproject.repositories.order.OrderRepo;
import kg.mega.rentserviceproject.repositories.order.PriceRepo;
import kg.mega.rentserviceproject.services.order.FormingOrderService;
import kg.mega.rentserviceproject.services.order.GetOrderService;
import kg.mega.rentserviceproject.services.order.OrderValidationService;
import kg.mega.rentserviceproject.services.order.SendMessageService;
import kg.mega.rentserviceproject.services.user.SessionService;
import kg.mega.rentserviceproject.services.user.UserValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Optional;

import static kg.mega.rentserviceproject.enums.DiscountValue.*;

@Service
@RequiredArgsConstructor
public class FormingOrderServiceImpl implements FormingOrderService {
    private final DiscountRepo discountRepo;
    private final PriceRepo priceRepo;
    private final OrderRepo orderRepo;
    private final CarRepo carRepo;

    private final SessionService sessionService;
    private final GetOrderService getOrderService;
    private final SendMessageService sendMessageService;
    private final UserValidationService userValidationService;
    private final OrderValidationService orderValidationService;

    @Override
    @Transactional
    public GetOrderDTO formingOrder(FormingOrderDTO formingOrderDTO) {
        String sessionId = formingOrderDTO.sessionId();
        int countDays = formingOrderDTO.countDays();
        long carId = formingOrderDTO.carId();

        User user = sessionService.getUserSession(sessionId);
        Car car = setRentStatus(carId);

        userValidationService.validateSession(sessionId, user.getUsername());
        orderValidationService.validateCountDays(countDays);

        LocalDate
            fromDate = formingOrderDTO.fromDate(),
            toDate = fromDate.plusDays(countDays);

        orderValidationService.dateValidate(fromDate);

        Discount discount = setDiscountType(countDays);
        BigDecimal totalPrice = calculateTotalPrice(carId, countDays, discount);

        Order order = orderRepo.save(new Order(user, car, discount, fromDate, toDate, countDays, totalPrice));

        if (!user.getEmail().isEmpty()) {
            sendMessageService.sendOrderToEmail(order);
        }

        return getOrderService.getOrder(order, sessionId);
    }

    private Car setRentStatus(long carId) {
        Optional<Car> optionalCar = carRepo.findById(carId);
        Car car = orderValidationService.validateCarAvailability(optionalCar);

        car.setRent(true);
        return car;
    }

    private Discount setDiscountType(Integer countDay) {
        long
            daysForDiscountValue = DAYS_FOR_DISCOUNT.getDiscountValue(),
            longTermLeasesDiscountId = LONG_TERM_LEASES_DISCOUNT_ID.getDiscountValue(),
            noDiscountId = NO_DISCOUNT_ID.getDiscountValue();

        return countDay >= daysForDiscountValue
               ? discountRepo.getReferenceById(longTermLeasesDiscountId)
               : discountRepo.getReferenceById(noDiscountId);
    }

    private BigDecimal calculateDiscount(BigDecimal totalPrice, Discount discount) {
        BigDecimal
            discountPercentage = discount.getDiscountPercentage(),
            discountAmount = totalPrice.multiply(discountPercentage.divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP));

        return totalPrice.subtract(discountAmount);
    }

    private BigDecimal calculateTotalPrice(long carId, int countDays, Discount discount) {
        BigDecimal
            price = priceRepo
                .findByCarId(carId)
                .orElseThrow(() -> new PriceNotFoundException("Invalid car id for price."))
                .getDayPrice(),
            totalPrice = price.multiply(BigDecimal.valueOf(countDays));

        return calculateDiscount(totalPrice, discount);
    }
}