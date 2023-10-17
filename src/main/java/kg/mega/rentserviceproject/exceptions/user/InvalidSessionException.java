package kg.mega.rentserviceproject.exceptions.user;

public class InvalidSessionException extends RuntimeException {
    public InvalidSessionException(String message) {
        super(message);
    }
}
