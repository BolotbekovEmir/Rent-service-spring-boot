package kg.mega.rentserviceproject.services.car;

import kg.mega.rentserviceproject.dto.car.CarCardDTO;

import java.util.List;

public interface GetCarCardService {
    CarCardDTO getCarCard(Long carId);
    List<CarCardDTO> getAllCarCards();
}
