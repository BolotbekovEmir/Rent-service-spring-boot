package kg.mega.rentserviceproject.dto.car;

import kg.mega.rentserviceproject.enums.BodyType;
import kg.mega.rentserviceproject.enums.ModelClass;

import java.math.BigDecimal;
import java.time.LocalDate;

public record CarCardDTO(
        Long carId,
        String modelName,
        String producerName,
        LocalDate yearProduction,
        BigDecimal dayPrice,
        BodyType bodyType,
        ModelClass modelClass,
        Boolean isRent,
        LocalDate endRentDate
) {
}