package by.itechart.web.integration.service;

import by.itechart.model.Student;
import by.itechart.model.exception.StudentNotFoundException;
import by.itechart.service.student.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static by.itechart.web.data.GeneralTestData.PAGE_PARAM;
import static by.itechart.web.data.StudentTestData.*;
import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@SpringBootTest
@ExtendWith(SpringExtension.class)
@Sql(scripts = { "/db/student/populate.sql" })
public class StudentServiceIntegrationTest {

    @Autowired
    private StudentService studentService;

    @Test
    public void shouldSaveStudentProperly() throws Throwable {

        log.info("Test saveStudent() method");

        Student student = studentService.saveStudent(TEST_STUDENT_1);
        Student expectedStudent = TEST_STUDENT_1;
        expectedStudent.setId(student.getId());

        assertThat(student)
                            .usingRecursiveComparison()
                            .ignoringFields("schoolClass")
                            .isEqualTo(expectedStudent);
    }


    @Test
    public void shouldGetByIdProperly() throws Throwable {

        log.info("Test getStudentById() method");

        Long studentId = TEST_STUDENT_2.getId();
        Long classId = TEST_SCHOOL_CLASS_1.getId();

        Student studentByIdAndClassId = studentService.getStudentByIdAndClassId(studentId, classId);

        assertThat(studentByIdAndClassId)
                                        .usingRecursiveComparison()
                                        .ignoringFields("schoolClass")
                                        .isEqualTo(TEST_STUDENT_2);
    }

    @Test
    public void shouldUpdateStudentProperly() throws Throwable {

        log.info("Test updateStudent() method");

        Long studentId = TEST_STUDENT_2.getId();
        Long classId = TEST_SCHOOL_CLASS_1.getId();
        Student updateStudent = UPDATE_STUDENT;

        Student updatedActualStudent = studentService.updateStudent(studentId, updateStudent, classId);
        updateStudent.setId(updatedActualStudent.getId());
        updateStudent.setSchoolClass(updatedActualStudent.getSchoolClass());

        assertThat(updatedActualStudent)
                                        .usingRecursiveComparison()
                                        .isEqualTo(updateStudent);
    }


    @Test
    public void shouldDeleteStudentProperly() {

        log.info("Test deleteStudent() method");
        Long studentId = TEST_STUDENT_2.getId();
        Long classId = TEST_SCHOOL_CLASS_1.getId();

        studentService.deleteStudentByIdAndClassId(studentId, classId);

        Assertions.assertThrows(StudentNotFoundException.class,
                                                () -> studentService.getStudentByIdAndClassId(studentId, classId));
    }


    @Test
    public void shouldFindAllStudentsProperly() {

        log.info("Test findAllStudent() method");
        Long classId = TEST_SCHOOL_CLASS_1.getId();
        List<Student> expectedStudents = List.of(
                                                TEST_STUDENT_2,
                                                TEST_STUDENT_3,
                                                TEST_STUDENT_4);

        List<Student> actualStudents = studentService.findAllStudents(classId, PAGE_PARAM);

        assertThat(actualStudents)
                                .usingRecursiveComparison()
                                .ignoringFields("schoolClass")
                                .isEqualTo(expectedStudents);
    }
}
