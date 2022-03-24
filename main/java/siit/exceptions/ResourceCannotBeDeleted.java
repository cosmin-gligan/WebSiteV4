package siit.exceptions;

public class ResourceCannotBeDeleted extends RuntimeException {
    public ResourceCannotBeDeleted(String message) {
        super(message);
    }
}
