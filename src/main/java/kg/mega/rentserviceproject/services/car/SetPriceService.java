package kg.mega.rentserviceproject.services.car;

import kg.mega.rentserviceproject.dto.car.SetPriceDTO;
import kg.mega.rentserviceproject.models.car.Price;

public interface SetPriceService {
    Price setPrice(SetPriceDTO setPriceDTO);
}
