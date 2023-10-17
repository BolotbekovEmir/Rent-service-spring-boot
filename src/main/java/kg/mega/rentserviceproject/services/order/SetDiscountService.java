package kg.mega.rentserviceproject.services.order;

import kg.mega.rentserviceproject.dto.order.SetDiscountDTO;
import kg.mega.rentserviceproject.models.order.Discount;

public interface SetDiscountService {
    Discount setDiscount(SetDiscountDTO discountDTO);
}
