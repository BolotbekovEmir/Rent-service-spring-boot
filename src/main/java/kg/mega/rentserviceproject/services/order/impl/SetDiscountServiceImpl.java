package kg.mega.rentserviceproject.services.order.impl;

import kg.mega.rentserviceproject.dto.order.SetDiscountDTO;
import kg.mega.rentserviceproject.models.order.Discount;
import kg.mega.rentserviceproject.repositories.order.DiscountRepo;
import kg.mega.rentserviceproject.services.order.SetDiscountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class SetDiscountServiceImpl implements SetDiscountService {
    private final DiscountRepo discountRepo;

    @Override
    public Discount setDiscount(SetDiscountDTO discountDTO) {
        return discountRepo.save(new Discount(
                discountDTO.name(),
                discountDTO.discountPercentage(),
                LocalDate.now()
        ));
    }
}
