package kg.mega.rentserviceproject.services.car.impl;

import kg.mega.rentserviceproject.dto.car.AddCarDTO;
import kg.mega.rentserviceproject.models.car.Car;
import kg.mega.rentserviceproject.repositories.car.CarRepo;
import kg.mega.rentserviceproject.repositories.car.ModelRepo;
import kg.mega.rentserviceproject.services.car.AddCarService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddCarServiceImpl implements AddCarService {
    private final CarRepo carRepo;
    private final ModelRepo modelRepo;

    @Override
    public Car addCar(AddCarDTO addCarDTO) {
        return carRepo.save(new Car(
                addCarDTO.vin(),
                addCarDTO.yearProduction(),
                modelRepo.getReferenceById(addCarDTO.modelId()),
                false,
                true
        ));
    }
}