package kg.mega.rentserviceproject.services.user;

import kg.mega.rentserviceproject.dto.user.GetUserProfileDTO;

public interface GetProfileService {
    GetUserProfileDTO getProfile(String sessionId);
}
