package kg.mega.rentserviceproject.services.user.impl;

import kg.mega.rentserviceproject.dto.user.GetUserProfileDTO;
import kg.mega.rentserviceproject.exceptions.user.UserNotFoundException;
import kg.mega.rentserviceproject.models.user.User;
import kg.mega.rentserviceproject.services.user.GetProfileService;
import kg.mega.rentserviceproject.services.user.SessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetProfileServiceImpl implements GetProfileService {
    private final SessionService sessionService;

    @Override
    public GetUserProfileDTO getProfile(String sessionId) {
        User user = sessionService.getUserSession(sessionId);

        if (user == null) {
            throw new UserNotFoundException("Invalid session.");
        }

        return new GetUserProfileDTO(
                user.getUsername(),
                user.getPhone(),
                user.getEmail(),
                user.getDateRegister()
        );
    }
}
