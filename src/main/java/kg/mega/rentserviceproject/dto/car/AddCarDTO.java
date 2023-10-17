package kg.mega.rentserviceproject.dto.car;

import java.time.LocalDate;

public record AddCarDTO(
        String vin,
        LocalDate yearProduction,
        Long modelId)
{}