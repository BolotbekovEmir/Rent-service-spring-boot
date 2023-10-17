package kg.mega.rentserviceproject.services.car.impl;

import kg.mega.rentserviceproject.dto.car.AddProducerDTO;
import kg.mega.rentserviceproject.models.car.Producer;
import kg.mega.rentserviceproject.repositories.car.ProducerRepo;
import kg.mega.rentserviceproject.services.car.AddProducerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddProducerServiceImpl implements AddProducerService {
    private final ProducerRepo producerRepo;

    @Override
    public Producer addProducer(AddProducerDTO addProducerDTO) {
        return producerRepo.save(new Producer(
                addProducerDTO.name(),
                addProducerDTO.country()
        ));
    }
}
