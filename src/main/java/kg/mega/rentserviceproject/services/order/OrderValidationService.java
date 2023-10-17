package kg.mega.rentserviceproject.services.order;

import kg.mega.rentserviceproject.models.car.Car;

import java.time.LocalDate;
import java.util.Optional;

public interface OrderValidationService {
    void dateValidate(LocalDate date);
    void validateCountDays(int countDays);
    Car validateCarAvailability(Optional<Car> car);
}
