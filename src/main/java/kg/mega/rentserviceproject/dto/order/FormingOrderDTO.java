package kg.mega.rentserviceproject.dto.order;

import java.time.LocalDate;

public record FormingOrderDTO(
        String sessionId,
        Long carId,
        LocalDate fromDate,
        Integer countDays)
{}