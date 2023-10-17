package kg.mega.rentserviceproject.dto.user;

public record ChangePasswordDTO(
        String sessionId,
        String oldPassword,
        String newPassword)
{}
