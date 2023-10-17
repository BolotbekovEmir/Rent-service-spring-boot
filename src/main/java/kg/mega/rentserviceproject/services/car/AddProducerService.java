package kg.mega.rentserviceproject.services.car;

import kg.mega.rentserviceproject.dto.car.AddProducerDTO;
import kg.mega.rentserviceproject.models.car.Producer;

public interface AddProducerService {
    Producer addProducer(AddProducerDTO addProducerDTO);
}
