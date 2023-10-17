package kg.mega.rentserviceproject.services.car.impl;

import kg.mega.rentserviceproject.dto.car.CarCardDTO;
import kg.mega.rentserviceproject.dto.car.FilteredSearchDTO;
import kg.mega.rentserviceproject.exceptions.car.IncorrectPriceException;
import kg.mega.rentserviceproject.repositories.car.CarRepo;
import kg.mega.rentserviceproject.services.car.FilteredSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FilteredSearchServiceImpl implements FilteredSearchService {
    private final CarRepo carRepo;

    @Override
    public List<CarCardDTO> filteredSearch(FilteredSearchDTO filteredSearchDTO) {
        List<CarCardDTO>
            catalog = carRepo.findAllCarsCards(),
            filtered = new ArrayList<>();

        String
            modelName = !filteredSearchDTO.modelName().isBlank()
                ? filteredSearchDTO.modelName().toLowerCase()
                : "",
            producerName = !filteredSearchDTO.producerName().isBlank()
                ? filteredSearchDTO.producerName().toLowerCase()
                : "";

        BigDecimal
            minPrice = filteredSearchDTO.minPrice(),
            maxPrice = filteredSearchDTO.maxPrice();

        catalog.forEach(car -> {
            if (isCarMatching(filteredSearchDTO, car, modelName, producerName, minPrice, maxPrice)) {
                filtered.add(car);
            }
        });

        return filtered;
    }

    private boolean isCarMatching(FilteredSearchDTO filter, CarCardDTO car, String modelName, String producerName, BigDecimal minPrice, BigDecimal maxPrice) {
        if (!modelName.isBlank() && !car.modelName().equalsIgnoreCase(modelName)) {
            return false;
        }

        if (!producerName.isBlank() && !car.producerName().equalsIgnoreCase(producerName)) {
            return false;
        }

        if (filter.minYearProduction() != null && car.yearProduction().isBefore(filter.minYearProduction())) {
            return false;
        }

        if (filter.maxYearProduction() != null && car.yearProduction().isAfter(filter.maxYearProduction())) {
            return false;
        }

        if (minPrice != null && maxPrice != null && minPrice.compareTo(maxPrice) > 0) {
            throw new IncorrectPriceException("The minimum price must not be greater than the maximum price.");
        }

        BigDecimal carPrice = car.dayPrice();
        if ((minPrice != null && carPrice.compareTo(minPrice) < 0) ||
                (maxPrice != null && carPrice.compareTo(maxPrice) > 0)) {
            return false;
        }

        if (filter.bodyType() != null && !car.bodyType().equals(filter.bodyType())) {
            return false;
        }

        return filter.modelClass() == null || car.modelClass().equals(filter.modelClass());
    }
}