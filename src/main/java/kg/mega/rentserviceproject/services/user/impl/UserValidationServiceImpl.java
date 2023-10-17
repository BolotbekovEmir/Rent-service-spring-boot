package kg.mega.rentserviceproject.services.user.impl;

import kg.mega.rentserviceproject.configurations.PatternConfig;
import kg.mega.rentserviceproject.exceptions.DuplicateException;
import kg.mega.rentserviceproject.exceptions.user.IncorrectFormatException;
import kg.mega.rentserviceproject.exceptions.user.InvalidSessionException;
import kg.mega.rentserviceproject.exceptions.user.UserNotFoundException;
import kg.mega.rentserviceproject.models.user.User;
import kg.mega.rentserviceproject.repositories.user.UserRepo;
import kg.mega.rentserviceproject.services.user.SessionService;
import kg.mega.rentserviceproject.services.user.UserValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserValidationServiceImpl implements UserValidationService {
    private final UserRepo userRepo;

    private final SessionService sessionService;

    @Override
    public void usernamePatternValid(String username) {
        if (!PatternConfig.USERNAME_PATTERN.matcher(username).matches()) {
            throw new IncorrectFormatException("Username does not match the specified pattern.");
        }
    }

    @Override
    public void passwordPatternValid(String password) {
        if (!PatternConfig.PASSWORD_PATTERN.matcher(password).matches()) {
            throw new IncorrectFormatException("Password does not match the specified pattern.");
        }
    }

    @Override
    public void phonePatternValid(String phone) {
        if (!PatternConfig.PHONE_PATTERN.matcher(phone).matches()) {
            throw new IncorrectFormatException("Phone does not match the specified pattern.");
        }
    }

    @Override
    public void emailPatternValid(String email) {
        if (!PatternConfig.EMAIL_PATTERN.matcher(email).matches()) {
            throw new IncorrectFormatException("Email does not match the specified pattern.");
        }
    }

    @Override
    public void usernameUniqueValid(String username) {
        if (userRepo.findByUsername(username).isPresent()) {
            throw new DuplicateException("This username is already in use.");
        }
    }

    @Override
    public void phoneUniqueValid(String phone) {
        if (userRepo.findByPhone(phone).isPresent()) {
            throw new DuplicateException("This phone is already in use.");
        }
    }

    @Override
    public void emailUniqueValid(String email) {
        if (userRepo.findByEmail(email).isPresent()) {
            throw new DuplicateException("This email is already in use.");
        }
    }

    @Override
    public boolean authValid(String username, String password) {
        if (userRepo.findByUsername(username).isPresent()) {
            return userRepo
                   .findByUsername(username)
                   .get()
                   .getPassword()
                   .equals(password);
        } else {
            return false;
        }
    }

    @Override
    public boolean isActiveUser(String username) {
        Optional<User> user = userRepo.findByUsername(username);
        return user.map(User::isActive).orElse(false);
    }

    public void validateSession(String sessionId, String username) {
        if (!sessionService.isCurrentSession(sessionId)) {
            throw new InvalidSessionException("This session is invalid.");
        }

        if (!sessionService.getUserSession(sessionId).getUsername().equals(username)) {
            throw new UserNotFoundException("Invalid username.");
        }
    }
}