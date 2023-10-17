package kg.mega.rentserviceproject.services.car.impl;

import kg.mega.rentserviceproject.models.car.Car;
import kg.mega.rentserviceproject.repositories.car.CarRepo;
import kg.mega.rentserviceproject.services.car.AttachImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Service
@RequiredArgsConstructor
public class AttachImageServiceImpl implements AttachImageService {
    private final CarRepo carRepo;

    @Override
    public void setImage(Long carId, MultipartFile file) {
        if (!file.isEmpty()) {
            try {
                String
                    pathImages = "C:\\Users\\User\\IdeaProjects\\RentServiceProject\\src\\main\\java\\kg\\mega\\rentserviceproject\\images",
                    imagePath = pathImages + File.separator + carId + "_" + file.getOriginalFilename();

                FileSystemResource resource = new FileSystemResource(imagePath);
                file.transferTo(resource.getFile());

                Car car = carRepo.findById(carId).orElse(null);

                if (car != null) {
                    car.setPathImg(imagePath);
                    carRepo.save(car);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}