package kg.mega.rentserviceproject.controllers;

import kg.mega.rentserviceproject.dto.car.*;
import kg.mega.rentserviceproject.models.car.Car;
import kg.mega.rentserviceproject.models.car.Model;
import kg.mega.rentserviceproject.models.car.Producer;
import kg.mega.rentserviceproject.models.car.Price;
import kg.mega.rentserviceproject.services.car.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/car")
@RequiredArgsConstructor
public class CarController {
    private final FilteredSearchService filteredSearchService;
    private final AddProducerService addProducerService;
    private final AttachImageService attachImageService;
    private final AddFullCarService addFullCarService;
    private final GetCarCardService getCarCardService;
    private final AddModelService addModelService;
    private final SetPriceService setPriceService;
    private final EditCarService editCarService;
    private final AddCarService addCarService;

    @PostMapping("add-full-car")
    public Car addFullCar(@RequestBody AddFullCarDTO addFullCarDTO) {
        return addFullCarService.addCar(addFullCarDTO);
    }

    @PostMapping("add-producer")
    public Producer addProducer(@RequestBody AddProducerDTO addProducerDTO) {
        return addProducerService.addProducer(addProducerDTO);
    }

    @PostMapping("add-model")
    public Model addModel(@RequestBody AddModelDTO addModelDTO) {
        return addModelService.addModel(addModelDTO);
    }

    @PostMapping("add-car")
    public Car addCar(@RequestBody AddCarDTO addCarDTO) {
        return addCarService.addCar(addCarDTO);
    }

    @PostMapping("set-car-price")
    public Price setPrice(@RequestBody SetPriceDTO setPriceDTO) {
        return setPriceService.setPrice(setPriceDTO);
    }

    @GetMapping("get-car-card")
    public CarCardDTO getCarCard(@RequestParam Long carId) {
        return getCarCardService.getCarCard(carId);
    }

    @GetMapping("get-all-car-cards")
    public List<CarCardDTO> getAllCarCards() {
        return getCarCardService.getAllCarCards();
    }

    @GetMapping("filter-search")
    public List<CarCardDTO> filteredSearch(@RequestBody FilteredSearchDTO filteredSearchDTO) {
        return filteredSearchService.filteredSearch(filteredSearchDTO);
    }

    @PatchMapping("edit-car")
    public Car editCar(@RequestBody EditCarDTO editCarDTO) {
        return editCarService.editCar(editCarDTO);
    }

    @PostMapping("attach-image-car")
    public ResponseEntity<String> attachImageCar(@RequestParam Long carId, @RequestParam MultipartFile image) {
        try {
            attachImageService.setImage(carId, image);
            return ResponseEntity.ok("File uploaded.");
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error when trying to upload an image: " + e.getMessage());
        }
    }
}