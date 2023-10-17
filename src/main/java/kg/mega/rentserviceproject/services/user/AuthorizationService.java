package kg.mega.rentserviceproject.services.user;

import kg.mega.rentserviceproject.dto.user.UserAuthDTO;

public interface AuthorizationService {
    String authorization(UserAuthDTO userAuthDTO);
}
