package by.itechart.mapping.dto.studentDetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentDetailsDto {

    private String firstParent = "";

    private String secondParent = "";

    @Email
    private String parentsEmail = "";

    @Pattern(regexp = "^[+]*[-\\s\\./0-9]{11}$")
    private String contactNum = "";

    private String bio = "";
}
