package ie.spring.planetsystem.SpaceManualImpl.exceptions;

public class BadRequestException extends RuntimeException {
    public BadRequestException(String message) {
        super(message);
    }
}
