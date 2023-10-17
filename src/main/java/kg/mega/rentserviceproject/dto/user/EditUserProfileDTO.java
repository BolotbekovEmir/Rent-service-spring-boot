package kg.mega.rentserviceproject.dto.user;

public record EditUserProfileDTO(
        String sessionId,
        String username,
        String phone,
        String email)
{}