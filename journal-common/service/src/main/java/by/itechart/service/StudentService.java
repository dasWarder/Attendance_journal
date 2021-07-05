package by.itechart.service;

import by.itechart.mapping.dto.StudentDto;

import java.util.List;

public interface StudentService {

    StudentDto saveStudent(StudentDto studentDto, Long classId) throws Throwable;

    StudentDto getStudentByIdAndClassId(Long studentId, Long classId) throws Throwable;

    StudentDto updateStudent(Long studentId, StudentDto studentDto, Long classId) throws Throwable;

    void deleteStudentByIdAndClassId(Long studentId, Long classId);

    List<StudentDto> findAllStudents(Long classId);
}
