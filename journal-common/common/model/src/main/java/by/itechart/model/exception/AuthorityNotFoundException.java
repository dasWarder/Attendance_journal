package by.itechart.model.exception;

public class AuthorityNotFoundException extends Throwable {

    public AuthorityNotFoundException(String message) {
        super(message);
    }

    public AuthorityNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
