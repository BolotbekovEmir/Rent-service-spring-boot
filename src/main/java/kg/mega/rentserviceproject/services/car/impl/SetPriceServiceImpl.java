package kg.mega.rentserviceproject.services.car.impl;

import kg.mega.rentserviceproject.dto.car.SetPriceDTO;
import kg.mega.rentserviceproject.exceptions.car.CarNotFoundException;
import kg.mega.rentserviceproject.models.car.Car;
import kg.mega.rentserviceproject.models.car.Price;
import kg.mega.rentserviceproject.repositories.car.CarRepo;
import kg.mega.rentserviceproject.repositories.order.PriceRepo;
import kg.mega.rentserviceproject.services.car.SetPriceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SetPriceServiceImpl implements SetPriceService {
    private final PriceRepo priceRepo;
    private final CarRepo carRepo;

    @Override
    public Price setPrice(SetPriceDTO setPriceDTO) {
        Car car = carRepo
                .findById(setPriceDTO.carId())
                .orElseThrow(() -> new CarNotFoundException("Invalid car id."));

        return priceRepo.save(new Price(
                car,
                setPriceDTO.price(),
                setPriceDTO.dayPrice()
        ));
    }
}