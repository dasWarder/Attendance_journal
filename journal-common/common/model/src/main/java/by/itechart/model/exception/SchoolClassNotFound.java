package by.itechart.model.exception;

public class SchoolClassNotFound extends Throwable {

    public SchoolClassNotFound(String message) {
        super(message);
    }

    public SchoolClassNotFound(String message, Throwable cause) {
        super(message, cause);
    }
}
