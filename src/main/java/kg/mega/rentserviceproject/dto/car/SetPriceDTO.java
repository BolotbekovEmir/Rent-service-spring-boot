package kg.mega.rentserviceproject.dto.car;

import java.math.BigDecimal;

public record SetPriceDTO(
        BigDecimal price,
        BigDecimal dayPrice,
        Long carId)
{}
