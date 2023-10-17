package kg.mega.rentserviceproject.dto.user;

import java.time.LocalDate;

public record GetUserProfileDTO(
        String name,
        String phone,
        String email,
        LocalDate dateRegister)
{}
