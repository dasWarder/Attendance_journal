package by.itechart.mapping.dto.absence;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AbsenceDto {

    @NotBlank
    private LocalDate absenceDate;

}
