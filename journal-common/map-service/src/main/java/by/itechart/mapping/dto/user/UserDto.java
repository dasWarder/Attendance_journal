package by.itechart.mapping.dto.user;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private Long id;

    @NotBlank(
            message = "The email must be not null or empty")
    @Email(message = "The field must be an email")
    private String email;

    @NotBlank(
            message = "The password must be not null or empty")
    @Size(min = 7, max = 156,
            message = "The password must be of size between 7 and 156 symbols")
    private String password;

    public UserDto(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
