package kg.mega.rentserviceproject.dto.order;

import kg.mega.rentserviceproject.dto.car.CarCardDTO;
import kg.mega.rentserviceproject.dto.user.GetUserProfileDTO;

import java.math.BigDecimal;
import java.time.LocalDate;

public record GetOrderDTO(
        GetUserProfileDTO getUserProfileDTO,
        CarCardDTO carCardDTO,
        BigDecimal discountPercentage,
        LocalDate fromDate,
        LocalDate toDate,
        Integer countDays,
        BigDecimal totalPrice)
{}
