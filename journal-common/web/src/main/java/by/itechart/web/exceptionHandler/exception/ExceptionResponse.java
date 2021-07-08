package by.itechart.web.exceptionHandler.exception;


import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExceptionResponse {

    private String className;

    private String message;
}
