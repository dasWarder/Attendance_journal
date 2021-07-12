package by.itechart.mapping.dto.user;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Data
@NoArgsConstructor
public class UserPassDto extends BaseUserDto{

    @NotBlank(
            message = "The password must be not null or empty")
    @Size(min = 7, max = 156,
            message = "The password must be of size between 7 and 156 symbols")
    private String password;

}
