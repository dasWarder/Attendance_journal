package by.itechart.model.exception;

public class StudentDetailsNotFoundException extends Throwable {

    public StudentDetailsNotFoundException(String message) {
        super(message);
    }

    public StudentDetailsNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
