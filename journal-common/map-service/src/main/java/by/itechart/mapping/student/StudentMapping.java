package by.itechart.mapping.student;

import by.itechart.mapping.dto.StudentDto;
import by.itechart.model.Student;

import java.util.List;

public interface StudentMapping {

    Student fromStudentDtoToStudent(StudentDto dto, Long classId) throws Throwable;

    StudentDto fromStudentToStudentDto(Student student);

    List<StudentDto> fromStudentListToStudentDtoList(List<Student> students);
}
