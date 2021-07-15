package by.itechart.model.exception;

public class UserAlreadyExistException extends Throwable{

    public UserAlreadyExistException(String message) {
        super(message);
    }

    public UserAlreadyExistException(String message, Throwable cause) {
        super(message, cause);
    }
}
