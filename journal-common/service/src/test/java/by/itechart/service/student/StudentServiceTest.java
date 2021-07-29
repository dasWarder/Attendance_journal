package by.itechart.service.student;

import by.itechart.model.Student;
import by.itechart.model.exception.StudentNotFoundException;
import by.itechart.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

import static by.itechart.service.TestData.*;
import static by.itechart.service.student.StudentTestData.*;
import static org.assertj.core.api.Assertions.assertThat;


@Slf4j
class StudentServiceTest {

    private StudentRepository studentRepository = Mockito.mock(StudentRepository.class);

    private StudentService studentService = new StudentServiceImpl(studentRepository);


    @Test
    public void shouldSaveMethodWorksProperly() throws Throwable {

        log.info("Test saveStudent() method");
        Mockito.when(studentRepository.save(TEST_STUDENT_1))
                                                            .thenReturn(TEST_STUDENT_1);

        Student student = studentService.saveStudent(TEST_STUDENT_1);

        assertThat(student)
                            .usingRecursiveComparison()
                            .isEqualTo(TEST_STUDENT_1);
    }

    @Test
    public void shouldThrowExceptionWhenSaveStudentIsNull() {

        log.info("Test saveStudent() method throw an exception");

        Assertions
                 .assertThrows(NullPointerException.class,
                                                            () -> studentService.saveStudent(null));
    }
    

    @Test
    public void shouldGetByIdMethodWorksProperly() throws Throwable {

        log.info("Test getStudentByIdAndClassId() method");
        Long studentId = TEST_STUDENT_1.getId();
        Long classId = TEST_SCHOOL_CLASS_1.getId();

        Mockito.when(studentRepository.getStudentByIdAndSchoolClassId(studentId, classId))
                                                                .thenReturn(Optional.of(TEST_STUDENT_1));

        Student studentByIdAndClassId = studentService.getStudentByIdAndClassId(studentId, classId);

        assertThat(studentByIdAndClassId)
                                        .usingRecursiveComparison()
                                        .isEqualTo(TEST_STUDENT_1);
    }


    @Test
    public void shouldThrowExceptionWhenTryToGetByWrongId() throws Throwable {

        log.info("Test getStudentByIdAndClassId() method");
        Long studentId = WRONG_ID;
        Long classId = TEST_SCHOOL_CLASS_1.getId();

        Mockito.when(studentRepository.getStudentByIdAndSchoolClassId(studentId, classId))
                                                                        .thenReturn(Optional.ofNullable(null));

        Assertions.assertThrows(StudentNotFoundException.class,
                                                    () -> studentService.getStudentByIdAndClassId(studentId, classId));
    }

    @Test
    public void shouldThrowExceptionWhenStudentIdNull() {

        log.info("Test getStudentByIdAndClassId() method throw an exception");
        Long classId = TEST_SCHOOL_CLASS_1.getId();

        Assertions
                  .assertThrows(NullPointerException.class,
                                            () -> studentService.getStudentByIdAndClassId(null, classId));
    }

    @Test
    public void shouldThrowExceptionWhenGetByIdClassIdNull() {

        log.info("Test getStudentByIdAndClassId() method throw an exception");
        Long studentId = TEST_STUDENT_1.getId();

        Assertions
                  .assertThrows(NullPointerException.class,
                                            () -> studentService.getStudentByIdAndClassId(studentId, null));
    }

    @Test
    public void shouldUpdateStudentMethodWorksProperly() throws Throwable {

        log.info("Test updateStudent() method");
        Mockito.when(studentService.saveStudent(TEST_STUDENT_1))
                                                                    .thenReturn(UPDATED_STUDENT_1);
        Long studentId = TEST_STUDENT_1.getId();
        Long classId = TEST_SCHOOL_CLASS_1.getId();

        Student student = studentService.updateStudent(studentId, TEST_STUDENT_1, classId);

        assertThat(student)
                            .usingRecursiveComparison()
                            .isEqualTo(UPDATED_STUDENT_1);
    }

    @Test
    public void shouldThrowExceptionWhenUpdateStudentIdNull() {

        log.info("Test updateStudent() method throw an exception");
        Long classId = TEST_SCHOOL_CLASS_1.getId();

        Assertions
                .assertThrows(NullPointerException.class,
                        () -> studentService.updateStudent(null,TEST_STUDENT_1, classId));
    }

    @Test
    public void shouldThrowExceptionWhenUpdateStudentNull() {

        log.info("Test updateStudent() method throw an exception");
        Long studentId = TEST_STUDENT_1.getId();
        Long classId = TEST_SCHOOL_CLASS_1.getId();


        Assertions
                .assertThrows(NullPointerException.class,
                        () -> studentService.updateStudent(studentId, null, classId));
    }

    @Test
    public void shouldThrowExceptionWhenUpdateClassIdNull() {

        log.info("Test updateStudent() method throw an exception");
        Long studentId = TEST_STUDENT_1.getId();

        Assertions
                .assertThrows(NullPointerException.class,
                        () -> studentService.updateStudent(studentId,TEST_STUDENT_1, null));
    }

    @Test
    public void shouldDeleteStudentByIdMethodWorksProperly() throws Throwable {

        log.info("Test deleteStudentById() method");
        Long studentId = TEST_STUDENT_1.getId();
        Long classId = TEST_SCHOOL_CLASS_1.getId();

        studentService.deleteStudentByIdAndClassId(studentId, classId);
    }

    @Test
    public void shouldThrowExceptionWhenDeleteStudentIdNull() {

        log.info("Test updateStudent() method throw an exception");
        Long classId = TEST_SCHOOL_CLASS_1.getId();

        Assertions
                .assertThrows(NullPointerException.class,
                        () -> studentService.deleteStudentByIdAndClassId(null, classId));
    }

    @Test
    public void shouldThrowExceptionWhenDeleteClassIdNull() {

        log.info("Test updateStudent() method throw an exception");
        Long studentId = TEST_STUDENT_1.getId();

        Assertions
                .assertThrows(NullPointerException.class,
                        () -> studentService.deleteStudentByIdAndClassId(studentId, null));
    }

    @Test
    public void shouldFindAllStudentsMethodWorksProperly() {

        log.info("Test findAllStudents() method");
        Long classId = TEST_SCHOOL_CLASS_1.getId();
        List<Student> expectedStudents = List.of(TEST_STUDENT_1, TEST_STUDENT_2, TEST_STUDENT_3);
        Mockito.when(studentRepository.findAllBySchoolClassId(classId, PAGE_PARAM)).thenReturn(expectedStudents);

        List<Student> actualStudents = studentService.findAllStudents(classId, PAGE_PARAM);

        assertThat(actualStudents)
                                    .usingRecursiveComparison()
                                    .isEqualTo(expectedStudents);
    }

    @Test
    public void shouldThrowExceptionWhenFindAllStudentsClassIdNull() {

        log.info("Test findAllStudentStudent() method throw an exception");

        Assertions
                .assertThrows(NullPointerException.class,
                        () -> studentService.findAllStudents(null, PAGE_PARAM));
    }


}