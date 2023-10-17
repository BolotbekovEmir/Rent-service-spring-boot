package kg.mega.rentserviceproject.services.user;

public interface UserValidationService {
    void usernamePatternValid(String username);
    void passwordPatternValid(String password);
    void phonePatternValid(String phone);
    void emailPatternValid(String email);

    void usernameUniqueValid(String username);
    void phoneUniqueValid(String phone);
    void emailUniqueValid(String email);

    boolean authValid(String username, String password);
    boolean isActiveUser(String username);

    void validateSession(String sessionId, String username);
}
