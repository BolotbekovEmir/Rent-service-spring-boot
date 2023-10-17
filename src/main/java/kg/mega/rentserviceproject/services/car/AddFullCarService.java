package kg.mega.rentserviceproject.services.car;

import kg.mega.rentserviceproject.dto.car.AddFullCarDTO;
import kg.mega.rentserviceproject.models.car.Car;

public interface AddFullCarService {
    Car addCar(AddFullCarDTO addFullCarDTO);
}
