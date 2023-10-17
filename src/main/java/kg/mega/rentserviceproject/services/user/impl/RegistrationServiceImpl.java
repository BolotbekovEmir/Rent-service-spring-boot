package kg.mega.rentserviceproject.services.user.impl;

import kg.mega.rentserviceproject.dto.user.UserRegisterDTO;
import kg.mega.rentserviceproject.models.user.User;
import kg.mega.rentserviceproject.repositories.user.UserRepo;
import kg.mega.rentserviceproject.services.user.RegistrationService;
import kg.mega.rentserviceproject.services.user.UserValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegistrationServiceImpl implements RegistrationService {
    private final UserRepo userRepo;

    private final UserValidationService validationService;

    @Override
    public UserRegisterDTO registration(UserRegisterDTO userRegisterDTO) {
        checkParams(userRegisterDTO);
        userRepo.save(new User(
                userRegisterDTO.username(),
                userRegisterDTO.phone(),
                userRegisterDTO.password()
        ));
        return userRegisterDTO;
    }

    private void checkParams(UserRegisterDTO userRegisterDTO) {
        validationService.usernamePatternValid(userRegisterDTO.username());
        validationService.phonePatternValid(userRegisterDTO.phone());
        validationService.passwordPatternValid(userRegisterDTO.password());

        validationService.usernameUniqueValid(userRegisterDTO.username());
        validationService.phoneUniqueValid(userRegisterDTO.phone());
    }
}
