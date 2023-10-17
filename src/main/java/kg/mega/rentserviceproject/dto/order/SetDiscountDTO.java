package kg.mega.rentserviceproject.dto.order;

import java.math.BigDecimal;

public record SetDiscountDTO(
        BigDecimal discountPercentage,
        String name
) {
}
