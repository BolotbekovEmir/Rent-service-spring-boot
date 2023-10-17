package kg.mega.rentserviceproject.services.user.impl;

import kg.mega.rentserviceproject.dto.user.ChangePasswordDTO;
import kg.mega.rentserviceproject.exceptions.user.IncorrectPasswordException;
import kg.mega.rentserviceproject.exceptions.user.UserNotFoundException;
import kg.mega.rentserviceproject.models.user.User;
import kg.mega.rentserviceproject.repositories.user.UserRepo;
import kg.mega.rentserviceproject.services.user.ChangePasswordService;
import kg.mega.rentserviceproject.services.user.SessionService;
import kg.mega.rentserviceproject.services.user.UserValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChangePasswordServiceImpl implements ChangePasswordService {
    private final UserRepo userRepo;

    private final SessionService sessionService;
    private final UserValidationService validationService;

    @Override
    public void changePassword(ChangePasswordDTO changePasswordDTO) {
        User user = sessionService.getUserSession(changePasswordDTO.sessionId());

        if (!sessionService.isCurrentSession(changePasswordDTO.sessionId())) {
            throw new UserNotFoundException("Invalid session id.");
        }

        checkPasswords(user, changePasswordDTO);

        user.setPassword(changePasswordDTO.newPassword());
        userRepo.save(user);
    }

    private void checkPasswords(User user, ChangePasswordDTO changePasswordDTO) {
        validationService.passwordPatternValid(changePasswordDTO.newPassword());

        if (!user.getPassword().equals(changePasswordDTO.oldPassword())) {
            throw new IncorrectPasswordException("Wrong old password.");
        }

        if (user.getPassword().equals(changePasswordDTO.newPassword())) {
            throw new IncorrectPasswordException("The new password cannot be the same as the old password.");
        }
    }
}