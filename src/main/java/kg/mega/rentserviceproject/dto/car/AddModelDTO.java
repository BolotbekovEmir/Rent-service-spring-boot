package kg.mega.rentserviceproject.dto.car;

import kg.mega.rentserviceproject.enums.BodyType;
import kg.mega.rentserviceproject.enums.ModelClass;

public record AddModelDTO(
        String name,
        BodyType bodyType,
        ModelClass modelClass,
        Long producerId)
{}