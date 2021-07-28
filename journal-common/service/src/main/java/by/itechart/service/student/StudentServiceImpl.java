package by.itechart.service.student;

import by.itechart.model.Student;
import by.itechart.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static by.itechart.model.util.ValidationUtil.validateOptional;
import static by.itechart.model.util.ValidationUtil.validateParams;

@Slf4j
@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    @Override
    @Transactional
    @CachePut(cacheNames = "students")
    public Student saveStudent(Student student) {

        validateParams(student);

        log.info("Storing a student for the class with ID = {}",
                                                                student.getSchoolClass().getId());

        Student storedStudent = studentRepository.save(student);

        return storedStudent;
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable(cacheNames = "students", key = "#studentId")
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
    @Transactional
    @CachePut(cacheNames = "students", key = "#studentId")
    public Student updateStudent(Long studentId, Student student, Long classId) {

        validateParams(studentId, student, classId);

        log.info("Update a student with ID = {} for a class with ID = {}",
                                                                          studentId, classId);
        student.setId(studentId);
        Student storedStudent = studentRepository.save(student);

        return storedStudent;
    }

    @Override
    @Transactional
    @CacheEvict(cacheNames = "students", key = "#studentId")
    public void deleteStudentByIdAndClassId(Long studentId, Long classId) {

        validateParams(studentId, classId);

        log.info("Remove a student with ID = {}",
                                                studentId);
        studentRepository.deleteStudentByIdAndSchoolClass_Id(studentId, classId);

    }

    @Override
    @Transactional(readOnly = true)
    public List<Student> findAllStudents(Long classId, Pageable pageable) {

        validateParams(classId, pageable);

        log.info("Receive a pageable collection of all student for a class with ID = {}",
                                                                                         classId);
        List<Student> studentsList = studentRepository.findAllBySchoolClass_Id(classId, pageable);

        return studentsList;
    }

    @Override
    @Transactional
    public List<Student> saveAllStudents(List<Student> students) {

        validateParams(students);

        log.info("Save a set of students");

        List<Student> studentList = (List<Student>) studentRepository.saveAll(students);

        return studentList;
    }

}
