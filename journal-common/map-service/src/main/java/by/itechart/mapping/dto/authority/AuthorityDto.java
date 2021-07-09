package by.itechart.mapping.dto.authority;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorityDto {

    private Long id;

    @NotBlank(
            message = "The name must be not null or empty")
    @Size(min = 1, max = 156,
            message = "The name size is between 1 and 156 symbols")
    private String name;

    public AuthorityDto(String name) {
        this.name = name;
    }
}
