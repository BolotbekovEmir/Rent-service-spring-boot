package kg.mega.rentserviceproject.services.user;

import kg.mega.rentserviceproject.dto.user.EditUserProfileDTO;
import kg.mega.rentserviceproject.dto.user.GetUserProfileDTO;

public interface EditProfileService {
    GetUserProfileDTO editProfile(EditUserProfileDTO editUserProfileDTO);
}
