package by.itechart.mapping.dto.student;


import lombok.*;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentDto {

    @NotBlank(
            message = "The name must be not null or empty")
    @Size(min = 2, max = 156,
          message = "The name must be of size between 2 and 156 symbols")
    private String name;

    @NotBlank(
            message = "The surname must be not null or empty")
    @Size(min = 2, max = 156,
            message = "The surname size must be between 2 and 156 symbols")
    private String surname;

}
