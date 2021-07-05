package by.itechart.service.student;

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

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student saveStudent(Student student, Long classId) {

        validateParams(student, classId);

        log.info("Storing a student for the class with ID = {}",
                                                                classId);
        Student storedStudent = studentRepository.save(student);

        return storedStudent;
    }

    @Override
    public Student getStudentByIdAndClassId(Long studentId, Long classId) throws Throwable {

        validateParams(studentId, classId);

        log.info("Receive a student by ID = {} for a school class with ID = {}",
                                                                                studentId, classId);
        Optional<Student> possibleStudentById = studentRepository.getStudentByIdAndSchoolClass_Id(
                                                                                                  studentId, classId);
        Student studentById = validateOptional(possibleStudentById, Student.class);

        return studentById;
    }

    @Override
    public Student updateStudent(Long studentId, Student student, Long classId) {

        validateParams(studentId, student, classId);

        log.info("Update a student with ID = {} for a class with ID = {}",
                                                                          studentId, classId);
        student.setId(studentId);
        Student storedStudent = studentRepository.save(student);

        return storedStudent;
    }

    @Override
    public void deleteStudentByIdAndClassId(Long studentId, Long classId) {

        validateParams(studentId, classId);

        log.info("Remove a student with ID = {}",
                                                studentId);
        studentRepository.deleteStudentByIdAndSchoolClass_Id(studentId, classId);

    }

    @Override
    public List<Student> findAllStudents(Long classId) {

        validateParams(classId);

        log.info("Receive a collection of all student for a class with ID = {}",
                                                                                classId);
        List<Student> studentsList = studentRepository.findAllBySchoolClass_Id(classId);

        return studentsList;
    }
}
