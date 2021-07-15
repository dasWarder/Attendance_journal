package by.itechart.mapping.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterUserDto extends BaseUserDto{

    @NotBlank(
              message = "The email must be not null or empty")
    @Size(min = 5,
         message = "The password size must be not less that 5")
    private String password;
}
