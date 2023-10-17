package kg.mega.rentserviceproject.services.car.impl;

import kg.mega.rentserviceproject.dto.car.AddFullCarDTO;
import kg.mega.rentserviceproject.exceptions.car.CarNotFoundException;
import kg.mega.rentserviceproject.models.car.Car;
import kg.mega.rentserviceproject.models.car.Model;
import kg.mega.rentserviceproject.models.car.Producer;
import kg.mega.rentserviceproject.models.car.Price;
import kg.mega.rentserviceproject.repositories.car.CarRepo;
import kg.mega.rentserviceproject.repositories.car.ModelRepo;
import kg.mega.rentserviceproject.repositories.car.ProducerRepo;
import kg.mega.rentserviceproject.repositories.order.PriceRepo;
import kg.mega.rentserviceproject.services.car.AddFullCarService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class AddFullCarServiceImpl implements AddFullCarService {
    private final CarRepo carRepo;
    private final ProducerRepo producerRepo;
    private final ModelRepo modelRepo;
    private final PriceRepo priceRepo;

    @Override
    public Car addCar(AddFullCarDTO addCarDTO) {
        Car car = carRepo.save(generateCar(addCarDTO));
        setPrice(addCarDTO);
        return car;
    }

    private Producer generateProducer(AddFullCarDTO addCarDTO) {
        Optional<Producer> producer = producerRepo.findByName(addCarDTO.producerName());
                return producer.orElseGet(() -> new Producer(
                addCarDTO.producerName(),
                addCarDTO.producerCountry()
        ));
    }

    private Model generateModel(AddFullCarDTO addCarDTO) {
        Optional<Model> model = modelRepo.findByName(addCarDTO.modelName());
        return model.orElseGet(() -> new Model(
                addCarDTO.modelName(),
                addCarDTO.bodyType(),
                addCarDTO.modelClass(),
                generateProducer(addCarDTO)
        ));
    }

    private Car generateCar(AddFullCarDTO addCarDTO) {
        return new Car(
                addCarDTO.vin(),
                addCarDTO.yearProduction(),
                generateModel(addCarDTO),
                false,
                true
        );
    }

    private void setPrice(AddFullCarDTO addFullCarDTO) {
        Car car = carRepo.findByVin(addFullCarDTO.vin()).orElseThrow(() -> new CarNotFoundException("Invalid car vin."));
        Price price = new Price();

        price.setPrice(addFullCarDTO.price());
        price.setDayPrice(addFullCarDTO.dayPrice());
        price.setSetDate(LocalDate.now());
        price.setCar(car);

        priceRepo.save(price);
    }
}