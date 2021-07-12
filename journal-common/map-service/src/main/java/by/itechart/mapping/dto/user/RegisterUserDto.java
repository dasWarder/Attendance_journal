package by.itechart.mapping.dto.user;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;


@Data
@NoArgsConstructor
public class RegisterUserDto extends BaseUserDto{

    @NotBlank(
              message = "The email must be not null or empty")
    @Min(value = 7,
         message = "The password size must be not less that 7")
    private String password;
}
