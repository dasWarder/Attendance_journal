package by.itechart.mapping.util;

import by.itechart.mapping.exception.SchoolClassNotFound;
import by.itechart.mapping.exception.StudentNotFoundException;
import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.Optional;


@Slf4j
public class ValidationUtil {


    public static void validateParams(Object... params) {

        log.info("Validate input params");

        Arrays.stream(params).forEach(param -> {
            if (param == null) {

                log.info("Input parameter with type = {} is NULL", param
                                                                        .getClass()
                                                                        .getName());

                throw new NullPointerException(String.format("The param of type = {} is NULL",
                                                                                              param
                                                                                                   .getClass()
                                                                                                   .getName()));
            }
        });
    }

    public static <T> T validateOptional(Optional<T> optionalObject, Class objectClass) throws Throwable {

        log.info("Validate an optional not to be equals NULL");

        Throwable exceptionMessage = getExceptionByClassName(
                                                            objectClass.getSimpleName());

        T validObject = optionalObject.orElseThrow(() -> exceptionMessage);

        return validObject;
    }

    private static Throwable getExceptionByClassName(String className) {

        switch (className) {

            case "Student":
                return new StudentNotFoundException("A student not found");

            case "SchoolClass":
                return new SchoolClassNotFound("A school class not found");

            default:
                return new NotFoundException("An object not found");
        }
    }
}
