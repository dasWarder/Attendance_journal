package by.itechart.mapping.dto.user;


import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UserDto extends BaseUserDto {

    @NotNull(
            message = "This field must have one of possible value")
    private boolean enabled;

}
