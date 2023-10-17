package kg.mega.rentserviceproject.services.car.impl;

import kg.mega.rentserviceproject.dto.car.AddModelDTO;
import kg.mega.rentserviceproject.exceptions.car.CarNotFoundException;
import kg.mega.rentserviceproject.models.car.Model;
import kg.mega.rentserviceproject.models.car.Producer;
import kg.mega.rentserviceproject.repositories.car.ModelRepo;
import kg.mega.rentserviceproject.repositories.car.ProducerRepo;
import kg.mega.rentserviceproject.services.car.AddModelService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AddModelServiceImpl implements AddModelService {
    private final ModelRepo modelRepo;
    private final ProducerRepo producerRepo;

    @Override
    public Model addModel(AddModelDTO addModelDTO) {
        Producer producer = producerRepo
                .findById(addModelDTO.producerId())
                .orElseThrow(() -> new CarNotFoundException("Invalid producer id."));

        return modelRepo.save(new Model(
                addModelDTO.name(),
                addModelDTO.bodyType(),
                addModelDTO.modelClass(),
                producer
        ));
    }
}
