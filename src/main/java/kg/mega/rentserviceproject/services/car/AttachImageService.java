package kg.mega.rentserviceproject.services.car;

import org.springframework.web.multipart.MultipartFile;

public interface AttachImageService {
    void setImage(Long carId, MultipartFile file);
}
