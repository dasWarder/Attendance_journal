package by.itechart.model.exception;

public class AbsenceNotFoundException extends Throwable {

    public AbsenceNotFoundException(String message) {
        super(message);
    }

    public AbsenceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
