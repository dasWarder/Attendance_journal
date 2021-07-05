package by.itechart.mapping.exception;

public class StudentNotFoundException extends Throwable {

    public StudentNotFoundException(String message) {
        super(message);
    }

    public StudentNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
