package by.itechart.service;

import by.itechart.mapping.dto.StudentDto;
import by.itechart.mapping.student.StudentMapping;
import by.itechart.model.Student;
import by.itechart.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static by.itechart.mapping.util.ValidationUtil.validateOptional;
import static by.itechart.mapping.util.ValidationUtil.validateParams;

@Slf4j
@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    private final StudentMapping studentMapping;

    public StudentServiceImpl(StudentRepository studentRepository, StudentMapping studentMapping) {
        this.studentRepository = studentRepository;
        this.studentMapping = studentMapping;
    }

    @Override
    public StudentDto saveStudent(StudentDto studentDto, Long classId) throws Throwable {

        validateParams(studentDto, classId);

        Student student = studentMapping.fromStudentDtoToStudent(studentDto, classId);
        log.info("Storing a student for the class with ID = {}",
                                                                classId);
        Student storedStudent = studentRepository.save(student);
        StudentDto dto = studentMapping.fromStudentToStudentDto(storedStudent);

        return dto;
    }

    @Override
    public StudentDto getStudentByIdAndClassId(Long studentId, Long classId) throws Throwable {

        validateParams(studentId, classId);

        log.info("Receive a student by ID = {} for a school class with ID = {}",
                                                                                studentId, classId);
        Optional<Student> possibleStudentById = studentRepository.getStudentByIdAndSchoolClass_Id(
                                                                                                  studentId, classId);
        Student studentById = validateOptional(possibleStudentById, Student.class);
        StudentDto dto = studentMapping.fromStudentToStudentDto(studentById);

        return dto;
    }

    @Override
    public StudentDto updateStudent(Long studentId, StudentDto studentDto, Long classId) throws Throwable {

        validateParams(studentId, studentDto, classId);

        log.info("Update a student with ID = {} for a class with ID = {}",
                                                                          studentId, classId);
        Student student = studentMapping.fromStudentDtoToStudent(studentDto, classId);
        student.setId(studentId);

        Student storedStudent = studentRepository.save(student);
        StudentDto dto = studentMapping.fromStudentToStudentDto(storedStudent);

        return dto;
    }

    @Override
    public void deleteStudentByIdAndClassId(Long studentId, Long classId) {

        validateParams(studentId, classId);

        log.info("Remove a student with ID = {}", studentId);
        studentRepository.deleteStudentByIdAndSchoolClass_Id(studentId, classId);

    }

    @Override
    public List<StudentDto> findAllStudents(Long classId) {

        validateParams(classId);

        log.info("Receive a collection of all student for a class with ID = {}",
                                                                                classId);
        List<Student> possibleStudentsList = studentRepository.findAllBySchoolClass_Id(classId);
        List<StudentDto> dtoList = studentMapping.fromStudentListToStudentDtoList(possibleStudentsList);

        return dtoList;
    }
}
