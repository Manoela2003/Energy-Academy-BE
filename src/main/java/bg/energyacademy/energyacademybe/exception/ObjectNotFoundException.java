package bg.energyacademy.energyacademybe.exception;

public class ObjectNotFoundException extends RuntimeException {
    public ObjectNotFoundException(String object) {
        super(String.format("%s не е намерен!", object));
    }

    public ObjectNotFoundException(String object, Throwable cause) {
        super(String.format("%s не е намерен!", object), cause);
    }

    public ObjectNotFoundException(Throwable cause) {
        super(cause);
    }

    public ObjectNotFoundException() {
        super("Не е намерен!");
    }
}