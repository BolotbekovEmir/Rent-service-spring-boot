package kg.mega.rentserviceproject.dto.car;

import java.time.LocalDate;

public record EditCarDTO(
    Long carId,
    Boolean isActive,
    Boolean isRent,
    String vin,
    LocalDate yearProduction)
{}