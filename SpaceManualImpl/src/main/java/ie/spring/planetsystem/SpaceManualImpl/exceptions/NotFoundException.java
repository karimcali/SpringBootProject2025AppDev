package ie.spring.planetsystem.SpaceManualImpl.exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}
