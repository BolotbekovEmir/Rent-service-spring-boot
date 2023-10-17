package kg.mega.rentserviceproject.dto.car;

import kg.mega.rentserviceproject.enums.BodyType;
import kg.mega.rentserviceproject.enums.ModelClass;

import java.math.BigDecimal;
import java.time.LocalDate;

public record AddFullCarDTO(
        String vin,
        LocalDate yearProduction,
        String modelName,
        BodyType bodyType,
        ModelClass modelClass,
        String producerName,
        String producerCountry,
        BigDecimal price,
        BigDecimal dayPrice)
{}