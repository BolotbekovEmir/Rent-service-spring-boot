package kg.mega.rentserviceproject.controllers;

import kg.mega.rentserviceproject.dto.user.*;
import kg.mega.rentserviceproject.services.user.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final AuthenticationService authenticationService;
    private final RegistrationService registrationService;
    private final ChangePasswordService passwordService;
    private final EditProfileService editProfileService;
    private final GetProfileService getProfileService;
    private final RemoveUserService removeUserService;

    @PostMapping("registration")
    public UserRegisterDTO registration(@RequestBody UserRegisterDTO userRegisterDTO) {
        return registrationService.registration(userRegisterDTO);
    }

    @PostMapping("authorization")
    public String authentication(@RequestBody UserAuthDTO userAuthDTO) {
        return authenticationService.authentication(userAuthDTO);
    }

    @GetMapping("get-profile")
    public GetUserProfileDTO getProfile(@RequestParam String sessionId) {
        return getProfileService.getProfile(sessionId);
    }

    @PatchMapping("edit-profile")
    public GetUserProfileDTO editProfile(@RequestBody EditUserProfileDTO editUserProfileDTO) {
        return editProfileService.editProfile(editUserProfileDTO);
    }

    @PatchMapping("change-password")
    public ResponseEntity<String> changePassword(@RequestBody ChangePasswordDTO changePasswordDTO) {
        try {
            passwordService.changePassword(changePasswordDTO);
            return ResponseEntity.ok("Password changed.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred when changing the password: " + e.getMessage());
        }
    }

    @PatchMapping("remove-user")
    public ResponseEntity<String> removeUser(@RequestBody RemoveUserDTO removeUserDTO) {
        try {
            removeUserService.removeUser(removeUserDTO);
            return ResponseEntity.ok("Deleted profile.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred when deleted the profile: " + e.getMessage());
        }
    }
}