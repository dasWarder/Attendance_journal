package by.itechart.mapping.student;


import by.itechart.mapping.dto.student.StudentDto;
import by.itechart.mapping.dto.student.StudentDtoId;
import by.itechart.model.Student;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Set;

@Mapper
public interface StudentMapper {

    @InheritInverseConfiguration
    StudentDto studentToStudentDto(Student student);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "schoolClass", ignore = true)
    Student studentDtoToStudent(StudentDto studentDto);

    @Mapping(target = "schoolClass", ignore = true)
    Student studentDtoIdToStudent(StudentDtoId studentDtoId);

    @Mapping(target = "schoolClass", ignore = true)
    List<StudentDtoId> studentListToStudentDtoIdList(List<Student> students);

    @Mapping(target = "schoolClass", ignore = true)
    @Mapping(target = "absenceDate", ignore = true)
    List<StudentDto> studentListToStudentDtoList(List<Student> students);
}
