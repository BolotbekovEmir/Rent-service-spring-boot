package kg.mega.rentserviceproject.dto.car;

import kg.mega.rentserviceproject.enums.BodyType;
import kg.mega.rentserviceproject.enums.ModelClass;

import java.math.BigDecimal;
import java.time.LocalDate;

public record FilteredSearchDTO(
        String modelName,
        String producerName,
        BodyType bodyType,
        ModelClass modelClass,
        LocalDate minYearProduction,
        LocalDate maxYearProduction,
        BigDecimal minPrice,
        BigDecimal maxPrice,
        Boolean isRent)
{}