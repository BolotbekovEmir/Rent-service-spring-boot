package kg.mega.rentserviceproject.exceptions.order;

public class PriceNotFoundException extends RuntimeException {
    public PriceNotFoundException(String message) {
        super(message);
    }
}
