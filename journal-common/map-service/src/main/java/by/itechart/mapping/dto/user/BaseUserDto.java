package by.itechart.mapping.dto.user;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseUserDto {

    private Long id;

    @NotBlank(
            message = "The email must be not null or empty")
    @Email(message = "The field must be an email")
    private String email;
}
