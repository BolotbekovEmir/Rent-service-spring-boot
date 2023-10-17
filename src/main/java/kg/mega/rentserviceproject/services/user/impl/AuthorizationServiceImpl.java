package kg.mega.rentserviceproject.services.user.impl;

import kg.mega.rentserviceproject.dto.user.UserAuthDTO;
import kg.mega.rentserviceproject.exceptions.user.AuthorizationException;
import kg.mega.rentserviceproject.exceptions.user.UserNotFoundException;
import kg.mega.rentserviceproject.models.user.User;
import kg.mega.rentserviceproject.repositories.user.UserRepo;
import kg.mega.rentserviceproject.services.user.AuthorizationService;
import kg.mega.rentserviceproject.services.user.SessionService;
import kg.mega.rentserviceproject.services.user.UserValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthorizationServiceImpl implements AuthorizationService {
    private final UserRepo userRepo;

    private final SessionService sessionService;
    private final UserValidationService validationService;

    @Override
    public String authorization(UserAuthDTO userAuthDTO) {
        String
            username = userAuthDTO.username(),
            password = userAuthDTO.password();

        if (!validationService.authValid(username, password)) {
            throw new AuthorizationException("Incorrect login or password.");
        }

        User user = userRepo
                .findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("Invalid username."));

        if (!validationService.isActiveUser(username)) {
            throw new UserNotFoundException("The status of this user is inactive.");
        }

        String sessionId = sessionService.generateSessionId();
        sessionService.addUserSession(sessionId, user);
        return sessionId;
    }
}