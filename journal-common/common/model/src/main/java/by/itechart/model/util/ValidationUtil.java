package by.itechart.model.util;

import by.itechart.model.exception.*;
import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.Optional;


@Slf4j
public class ValidationUtil {

    /**
     * A method to validate input params for services. If Null throw NullPointerException
     * @param params input params of a service method, that must be validated
     */
    public static void validateParams(Object... params) {

        log.info("Validate input params");

        Arrays.stream(params).forEach(param -> {

            if (param == null) {
                log.error("Input parameter with type = {} is NULL", param
                                                                        .getClass()
                                                                        .getName());
                throw new NullPointerException(String.format("The param of type = {} is NULL",
                                                                                              param
                                                                                                   .getClass()
                                                                                                   .getName()));
            }
        });
    }

    /**
     * A method for validating optional objects.
     * @param optionalObject
     * @param objectClass in case of an exception, the class allows to select a correct exception type and a message
     * @param <T> Any class, which optional must be validated
     * @return valid object of T type, received from the optional
     * @throws Throwable an exception of various types, if the optional is empty
     */
    public static <T> T validateOptional(Optional<T> optionalObject, Class objectClass) throws Throwable {

        log.info("Validate an optional not to be equals NULL");

        Throwable exceptionMessage = getExceptionByClassName(
                                                            objectClass.getSimpleName());

        T validObject = optionalObject.orElseThrow(() -> {

            log.error("The exception has occurred for the class = {}",
                                                                     objectClass.getName());
            return exceptionMessage;
        });

        return validObject;
    }

    private static Throwable getExceptionByClassName(String className) {

        switch (className) {

            case "Student":
                return new StudentNotFoundException("A student not found");

            case "SchoolClass":
                return new SchoolClassNotFound("A school class not found");

            case "User":
                return new UserNotFoundException("A user not found");

            case "UserAuthority":
                return new AuthorityNotFoundException("An authority not found");

            case "RefreshToken":
                return new TokenNotFoundException("A refresh token not found");

            case "Absence":
                return new AbsenceNotFoundException("An absence object not found");

            default:
                return new NotFoundException("An object not found");
        }
    }
}
