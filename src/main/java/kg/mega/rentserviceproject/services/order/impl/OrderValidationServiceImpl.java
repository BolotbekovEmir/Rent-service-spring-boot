package kg.mega.rentserviceproject.services.order.impl;

import kg.mega.rentserviceproject.exceptions.car.CarNotFoundException;
import kg.mega.rentserviceproject.exceptions.order.OrderDateException;
import kg.mega.rentserviceproject.models.car.Car;
import kg.mega.rentserviceproject.services.order.OrderValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderValidationServiceImpl implements OrderValidationService {
    @Override
    public void dateValidate(LocalDate date) {
        if (date.isBefore(LocalDate.now())) {
            throw new OrderDateException("Incorrect date: the start date of the lease must not be earlier than today's date.");
        }
    }

    @Override
    public void validateCountDays(int countDays) {
        if (countDays < 1) {
            throw new IllegalArgumentException("Count days must be greater than or equal to 1.");
        }
    }

    @Override
    public Car validateCarAvailability(Optional<Car> car) {
        if (car.isEmpty()) {
            throw new CarNotFoundException("Invalid car id.");
        }

        if (car.get().isRent()) {
            throw new CarNotFoundException("This car is already on a leases.");
        }

        if (!car.get().isActive()) {
            throw new CarNotFoundException("This car is inactive status.");
        }

        return car.get();
    }
}
