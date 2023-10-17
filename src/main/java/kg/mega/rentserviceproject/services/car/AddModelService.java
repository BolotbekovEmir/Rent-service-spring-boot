package kg.mega.rentserviceproject.services.car;

import kg.mega.rentserviceproject.dto.car.AddModelDTO;
import kg.mega.rentserviceproject.models.car.Model;

public interface AddModelService {
    Model addModel(AddModelDTO addModelDTO);
}
