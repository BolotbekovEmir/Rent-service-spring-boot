package kg.mega.rentserviceproject.exceptions.user;

public class AuthorizationException extends RuntimeException {
    public AuthorizationException(String message) {
        super(message);
    }
}
