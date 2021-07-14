package by.itechart.mapping.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class FullUserDto extends BaseUserDto {

    @JsonProperty(index = 1)
    private Long id;

    @NotBlank(
              message = "The password must be not null or empty")
    @Size(min = 6, max = 156,
            message = "The password must be of size between 6 and 156 symbols")
    private String password;

    @NotNull(
              message = "This field must have one of possible value")
    private boolean enabled;
}
