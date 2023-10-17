package kg.mega.rentserviceproject.services.user;

import kg.mega.rentserviceproject.dto.user.UserRegisterDTO;
import kg.mega.rentserviceproject.models.user.User;

public interface RegistrationService {
    UserRegisterDTO registration(UserRegisterDTO userRegisterDTO);
}
