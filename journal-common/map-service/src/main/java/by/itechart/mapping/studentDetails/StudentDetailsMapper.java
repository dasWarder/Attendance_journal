package by.itechart.mapping.studentDetails;

import by.itechart.mapping.dto.studentDetails.StudentDetailsDto;
import by.itechart.model.StudentDetails;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface StudentDetailsMapper {

    StudentDetails studentDetailsDtoToStudentDetails(StudentDetailsDto detailsDto);

    StudentDetailsDto studentDetailsToStudentDetailsDto(StudentDetails studentDetails);
}
