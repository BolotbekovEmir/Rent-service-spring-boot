package kg.mega.rentserviceproject.services.car;

import kg.mega.rentserviceproject.dto.car.AddCarDTO;
import kg.mega.rentserviceproject.models.car.Car;

public interface AddCarService {
    Car addCar(AddCarDTO addCarDTO);
}
