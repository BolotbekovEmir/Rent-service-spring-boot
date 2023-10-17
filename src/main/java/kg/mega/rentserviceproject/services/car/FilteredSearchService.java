package kg.mega.rentserviceproject.services.car;

import kg.mega.rentserviceproject.dto.car.CarCardDTO;
import kg.mega.rentserviceproject.dto.car.FilteredSearchDTO;

import java.util.List;

public interface FilteredSearchService {
    List<CarCardDTO> filteredSearch(FilteredSearchDTO filteredSearchDTO);
}
