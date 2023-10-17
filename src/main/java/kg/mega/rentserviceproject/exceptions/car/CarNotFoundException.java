package kg.mega.rentserviceproject.exceptions.car;

public class CarNotFoundException extends RuntimeException {
    public CarNotFoundException(String message) {
        super(message);
    }
}
