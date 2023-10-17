package kg.mega.rentserviceproject.services.car.impl;

import kg.mega.rentserviceproject.dto.car.CarCardDTO;
import kg.mega.rentserviceproject.exceptions.car.CarNotFoundException;
import kg.mega.rentserviceproject.repositories.car.CarRepo;
import kg.mega.rentserviceproject.services.car.GetCarCardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetCarCardServiceImpl implements GetCarCardService {
    private final CarRepo carRepo;

    @Override
    public CarCardDTO getCarCard(Long carId) {
        return carRepo
                .findCarById(carId)
                .orElseThrow(() -> new CarNotFoundException("Invalid car id."));
    }

    @Override
    public List<CarCardDTO> getAllCarCards() {
        return carRepo.findAllCarsCards();
    }
}