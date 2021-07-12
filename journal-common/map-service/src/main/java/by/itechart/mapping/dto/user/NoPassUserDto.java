package by.itechart.mapping.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class NoPassUserDto extends BaseUserDto {

    private Long id;

    @NotNull(
            message = "This field must have one of possible value")
    private boolean enabled;

    @NotBlank(
            message = "The role must be not null or empty")
    @Size(min = 4, max = 156,
            message = "The role must be of size between 4 and 156 symbols")
    private String role;
}
