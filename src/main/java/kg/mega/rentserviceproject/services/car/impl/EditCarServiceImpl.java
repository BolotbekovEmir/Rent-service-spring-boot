package kg.mega.rentserviceproject.services.car.impl;

import kg.mega.rentserviceproject.dto.car.EditCarDTO;
import kg.mega.rentserviceproject.exceptions.car.CarNotFoundException;
import kg.mega.rentserviceproject.models.car.Car;
import kg.mega.rentserviceproject.repositories.car.CarRepo;
import kg.mega.rentserviceproject.services.car.EditCarService;
import kg.mega.rentserviceproject.services.car.GetCarCardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EditCarServiceImpl implements EditCarService {
    private final CarRepo carRepo;

    private final GetCarCardService getCarCardService;

    @Override
    public Car editCar(EditCarDTO editCarDTO) {
        Car car = carRepo
                .findById(editCarDTO.carId())
                .orElseThrow(() -> new CarNotFoundException("Invalid car id."));

        updateCarData(car, editCarDTO);

        carRepo.save(car);

        return carRepo
                .findById(getCarCardService.getCarCard(car.getId()).carId())
                .orElseThrow(() -> new CarNotFoundException("Invalid car id."));
    }

    private void updateCarData(Car car, EditCarDTO editCarDTO) {
        if (!editCarDTO.vin().isBlank()) {
            car.setVin(editCarDTO.vin());
        }

        if (!editCarDTO.yearProduction().toString().isBlank()) {
            car.setYearProduction(editCarDTO.yearProduction());
        }

        if (editCarDTO.isActive() != null) {
            car.setActive(editCarDTO.isActive());
        }

        if (editCarDTO.isRent() != null) {
            car.setRent(editCarDTO.isRent());
        }
    }
}
