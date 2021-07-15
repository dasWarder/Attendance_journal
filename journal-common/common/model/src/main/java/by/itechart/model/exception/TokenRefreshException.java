package by.itechart.model.exception;


public class TokenRefreshException extends Throwable {

    public TokenRefreshException(String message) {
        super(message);
    }

    public TokenRefreshException(String message, Throwable cause) {
        super(message, cause);
    }
}
