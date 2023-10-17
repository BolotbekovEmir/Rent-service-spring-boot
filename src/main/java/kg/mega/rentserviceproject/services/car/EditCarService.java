package kg.mega.rentserviceproject.services.car;

import kg.mega.rentserviceproject.dto.car.EditCarDTO;
import kg.mega.rentserviceproject.models.car.Car;

public interface EditCarService {
    Car editCar(EditCarDTO editCarDTO);
}
