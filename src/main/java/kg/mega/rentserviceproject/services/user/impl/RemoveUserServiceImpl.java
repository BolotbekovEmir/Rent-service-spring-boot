package kg.mega.rentserviceproject.services.user.impl;

import kg.mega.rentserviceproject.dto.user.RemoveUserDTO;
import kg.mega.rentserviceproject.exceptions.user.IncorrectPasswordException;
import kg.mega.rentserviceproject.exceptions.user.UserNotFoundException;
import kg.mega.rentserviceproject.models.user.User;
import kg.mega.rentserviceproject.repositories.user.UserRepo;
import kg.mega.rentserviceproject.services.user.RemoveUserService;
import kg.mega.rentserviceproject.services.user.SessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RemoveUserServiceImpl implements RemoveUserService {
    private final UserRepo userRepo;

    private final SessionService sessionService;

    @Override
    public void removeUser(RemoveUserDTO removeUserDTO) {
        String
            sessionId = removeUserDTO.sessionId(),
            password  = removeUserDTO.password();

        if (!sessionService.isCurrentSession(sessionId)) {
            throw new UserNotFoundException("Invalid session.");
        }

        User user = sessionService.getUserSession(sessionId);

        if (!user.getPassword().equals(password)) {
            throw new IncorrectPasswordException("Invalid password.");
        }

        user.setActive(false);
        sessionService.removeUserSession(sessionId);
        userRepo.save(user);
    }
}