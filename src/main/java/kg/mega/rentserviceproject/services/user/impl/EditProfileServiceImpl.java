package kg.mega.rentserviceproject.services.user.impl;

import kg.mega.rentserviceproject.dto.user.EditUserProfileDTO;
import kg.mega.rentserviceproject.dto.user.GetUserProfileDTO;
import kg.mega.rentserviceproject.exceptions.user.UserNotFoundException;
import kg.mega.rentserviceproject.models.user.User;
import kg.mega.rentserviceproject.repositories.user.UserRepo;
import kg.mega.rentserviceproject.services.user.EditProfileService;
import kg.mega.rentserviceproject.services.user.GetProfileService;
import kg.mega.rentserviceproject.services.user.SessionService;
import kg.mega.rentserviceproject.services.user.UserValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EditProfileServiceImpl implements EditProfileService {
    private final UserRepo userRepo;

    private final SessionService sessionService;
    private final GetProfileService getProfileService;
    private final UserValidationService validationService;

    @Override
    public GetUserProfileDTO editProfile(EditUserProfileDTO editUserProfileDTO) {
        String sessionId = editUserProfileDTO.sessionId();

        if (!sessionService.isCurrentSession(sessionId)) {
            throw new UserNotFoundException("Invalid session.");
        }

        User user = sessionService.getUserSession(sessionId);
        updateUserData(user, editUserProfileDTO);

        userRepo.save(user);
        return getProfileService.getProfile(sessionId);
    }

    private void updateUserData(User user, EditUserProfileDTO editUserProfileDTO) {
        String
            username = editUserProfileDTO.username(),
            phone = editUserProfileDTO.phone(),
            email = editUserProfileDTO.email();

        if (!username.isBlank()) {
            validationService.usernamePatternValid(username);
            validationService.usernameUniqueValid(username);
            user.setUsername(username);
        }

        if (!phone.isBlank()) {
            validationService.phonePatternValid(phone);
            validationService.phoneUniqueValid(phone);
            user.setPhone(phone);
        }

        if (!email.isBlank()) {
            validationService.emailPatternValid(email);
            validationService.emailUniqueValid(email);
            user.setEmail(email);
        }
    }
}