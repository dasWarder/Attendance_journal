package by.itechart.model.exception;

public class TokenNotFoundException extends Throwable {

    public TokenNotFoundException(String message) {
        super(message);
    }

    public TokenNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
