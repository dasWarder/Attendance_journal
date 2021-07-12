package by.itechart.mapping.dto.schoolClass;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SchoolClassDto {

    @JsonProperty(index = 1)
    private Long id;

    @NotBlank(
            message = "The name must be not null or empty")
    @Size(min = 1, max = 156,
          message = "The classname size is between 1 and 156 symbols")
    private String name;

}
