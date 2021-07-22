package by.itechart.service.attending;

import by.itechart.model.Student;
import by.itechart.service.absence.AbsenceService;
import by.itechart.service.absence.AbsenceServiceImpl;
import by.itechart.service.student.StudentService;
import by.itechart.service.student.StudentServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static by.itechart.service.TestData.*;
import static by.itechart.service.attending.StudentAbsenceTestData.*;
import static by.itechart.service.student.StudentTestData.TEST_STUDENT_1;
import static org.assertj.core.api.Assertions.assertThat;


@Slf4j
class StudentAbsenceServiceTest {

    private final StudentService studentService = Mockito.mock(StudentServiceImpl.class);

    private final AbsenceService absenceService = Mockito.mock(AbsenceServiceImpl.class);

    private final StudentAbsenceService studentAbsenceService = new StudentAbsenceServiceImpl(studentService, absenceService);

    @Test
    public void shouldReturnAllAbsenceStudentsByAbsenceDateAndClassIdProperly() throws Throwable {

        log.info("Test getAllByAbsenceDatesAndSchoolClassId() method");

        Long classId = TEST_SCHOOL_CLASS_1.getId();
        Mockito.when(absenceService.getAbsenceByAbsenceDate(TEST_ABSENCE_DATE))
                                                                               .thenReturn(TEST_ABSENCE);
        Mockito.when(studentService.findAllStudents(classId, PAGE_PARAM))
                                                             .thenReturn(TEST_ALL_STUDENTS);

        List<Student> actualAbsenceStudents = studentAbsenceService
                                                        .getAllByAbsenceDatesAndSchoolClassId(TEST_ABSENCE_DATE, classId, PAGE_PARAM);

        assertThat(actualAbsenceStudents)
                                        .usingRecursiveComparison()
                                        .isEqualTo(TEST_ABSENCE_STUDENT_LIST);
    }

    @Test
    public void shouldGetAllByAbsenceDateThrowExceptionWhenFirstParamIsNull() {

        log.info("Test getAllByAbsenceDatesAndSchoolClassId() method throw an exception");

        Long classId = TEST_SCHOOL_CLASS_1.getId();

        Assertions.assertThrows(NullPointerException.class,
                                                () -> studentAbsenceService
                                                            .getAllByAbsenceDatesAndSchoolClassId(null, classId, PAGE_PARAM));
    }

    @Test
    public void shouldGetAllByAbsenceDateThrowExceptionWhenSecondParamIsNull() {

        log.info("Test getAllByAbsenceDatesAndSchoolClassId() method throw an exception");

        Assertions.assertThrows(NullPointerException.class,
                                                () -> studentAbsenceService
                                                          .getAllByAbsenceDatesAndSchoolClassId(TEST_ABSENCE_DATE,null, PAGE_PARAM));
    }

    @Test
    public void shouldGetAllByAbsenceDateThrowExceptionWhenFirstParamIsWrong() {

        log.info("Test getAllByAbsenceDatesAndSchoolClassId() method throw an exception");

        Long classId = TEST_SCHOOL_CLASS_1.getId();

        Assertions.assertThrows(NullPointerException.class,
                                    () -> studentAbsenceService.getAllByAbsenceDatesAndSchoolClassId(WRONG_ABSENCE_DATE, classId, PAGE_PARAM));
    }

    @Test
    public void shouldGetAllByAbsenceDateThrowExceptionWhenSecondParamIsWrong() {

        log.info("Test getAllByAbsenceDatesAndSchoolClassId() method throw an exception");

        Assertions.assertThrows(NullPointerException.class,
                () -> studentAbsenceService.getAllByAbsenceDatesAndSchoolClassId(TEST_ABSENCE_DATE, WRONG_ID, PAGE_PARAM));
    }

    @Test
    public void shouldSaveStudentIntoAbsenceListProperly() throws Throwable {

        log.info("Test addStudentToAbsenceList() method");

        Long classId = TEST_SCHOOL_CLASS_1.getId();
        Long studentId = TEST_NEW_ABSENCE_STUDENT.getId();
        Mockito.when(absenceService.getAbsenceByAbsenceDate(TEST_ABSENCE_DATE)).thenReturn(TEST_ABSENCE);
        Mockito.when(studentService.getStudentByIdAndClassId(studentId, classId)).thenReturn(TEST_NEW_ABSENCE_STUDENT);

        Student absenceStudent = TEST_NEW_ABSENCE_STUDENT;
        absenceStudent.getAbsenceDates().add(TEST_ABSENCE);
        Mockito.when(studentService.saveStudent(TEST_NEW_ABSENCE_STUDENT)).thenReturn(absenceStudent);

        Student student = studentAbsenceService.addStudentToAbsenceList(classId, studentId, TEST_ABSENCE_DATE);

        assertThat(student)
                            .usingRecursiveComparison()
                            .isEqualTo(TEST_NEW_ABSENCE_STUDENT);
    }

    @Test
    public void shouldAddStudentToAbsenceListThrowExceptionWhenFirstParamIsNull() {

        log.info("Test addStudentToAbsenceList() method throw an exception");

        Long studentId = TEST_NEW_ABSENCE_STUDENT.getId();

        Assertions.assertThrows(NullPointerException.class,
                                    () -> studentAbsenceService.addStudentToAbsenceList(null, studentId, TEST_ABSENCE_DATE));
    }

    @Test
    public void shouldAddStudentToAbsenceListThrowExceptionWhenSecondParamIsNull() {

        log.info("Test addStudentToAbsenceList() method throw an exception");

        Long classId = TEST_SCHOOL_CLASS_1.getId();

        Assertions.assertThrows(NullPointerException.class,
                () -> studentAbsenceService.addStudentToAbsenceList(classId, null, TEST_ABSENCE_DATE));
    }

    @Test
    public void shouldAddStudentToAbsenceListThrowExceptionWhenThirdParamIsNull() {

        log.info("Test addStudentToAbsenceList() method throw an exception");

        Long classId = TEST_SCHOOL_CLASS_1.getId();
        Long studentId = TEST_NEW_ABSENCE_STUDENT.getId();

        Assertions.assertThrows(NullPointerException.class,
                () -> studentAbsenceService.addStudentToAbsenceList(classId, studentId, null));
    }

    @Test
    public void shouldAddStudentToAbsenceListThrowExceptionWhenFirstParamIsWrong() {

        log.info("Test addStudentToAbsenceList() method throw an exception");

        Long classId = WRONG_ID;
        Long studentId = TEST_NEW_ABSENCE_STUDENT.getId();

        Assertions.assertThrows(NullPointerException.class,
                () -> studentAbsenceService.addStudentToAbsenceList(classId, studentId, TEST_ABSENCE_DATE));
    }

    @Test
    public void shouldAddStudentToAbsenceListThrowExceptionWhenSecondParamIsWrong() {

        log.info("Test addStudentToAbsenceList() method throw an exception");

        Long classId = TEST_SCHOOL_CLASS_1.getId();
        Long studentId = WRONG_ID;

        Assertions.assertThrows(NullPointerException.class,
                () -> studentAbsenceService.addStudentToAbsenceList(classId, studentId, TEST_ABSENCE_DATE));
    }

    @Test
    public void shouldAddStudentToAbsenceListThrowExceptionWhenThirdParamIsWrong() {

        log.info("Test addStudentToAbsenceList() method throw an exception");

        Long classId = TEST_SCHOOL_CLASS_1.getId();
        Long studentId = TEST_NEW_ABSENCE_STUDENT.getId();

        Assertions.assertThrows(NullPointerException.class,
                () -> studentAbsenceService.addStudentToAbsenceList(classId, studentId, WRONG_ABSENCE_DATE));
    }

    @Test
    public void shouldSaveAListOfStudentsToAbsenceListProperly() throws Throwable {

        log.info("Test addStudentsToAbsenceList() method");

        Student absenceStudent = TEST_NEW_ABSENCE_STUDENT;
        List<Student> requestList = List.of(absenceStudent);

        absenceStudent.getAbsenceDates().add(TEST_ABSENCE);
        List<Student> responseList = List.of(absenceStudent);

        Mockito.when(absenceService.getAbsenceByAbsenceDate(TEST_ABSENCE_DATE)).thenReturn(TEST_ABSENCE);
        Mockito.when(studentService.saveAllStudents(responseList)).thenReturn(responseList);

        List<Student> actualStudentList = studentAbsenceService.addStudentsToAbsenceList(requestList, TEST_ABSENCE_DATE);

        assertThat(actualStudentList)
                                    .usingRecursiveComparison()
                                    .isEqualTo(responseList);
    }

    @Test
    public void shouldSaveAListOfStudentsThrowExceptionWhenFirstParamIsNull() {

        log.info("Test addStudentsToAbsenceList() method throw an exception");

        Assertions.assertThrows(NullPointerException.class,
                                             () -> studentAbsenceService.addStudentsToAbsenceList(null, TEST_ABSENCE_DATE));
    }

    @Test
    public void shouldSaveAListOfStudentsThrowExceptionWhenSecondParamIsNull() {

        log.info("Test addStudentsToAbsenceList() method throw an exception");

        List<Student> requestStudents = List.of(TEST_NEW_ABSENCE_STUDENT);

        Assertions.assertThrows(NullPointerException.class,
                () -> studentAbsenceService.addStudentsToAbsenceList(requestStudents, null));
    }

    @Test
    public void shouldSaveAListOfStudentsThrowExceptionWhenFirstParamIsWrong() {

        log.info("Test addStudentsToAbsenceList() method throw an exception");

        List<Student> requestStudents = List.of(TEST_STUDENT_1);

        Assertions.assertThrows(NullPointerException.class,
                () -> studentAbsenceService.addStudentsToAbsenceList(requestStudents, TEST_ABSENCE_DATE));
    }

    @Test
    public void shouldDeleteStudentFromAbsenceListProperly() throws Throwable {

        log.info("Test deleteStudentFromAbsenceList() method");

        Student absenceStudent = TEST_NEW_ABSENCE_STUDENT;
        absenceStudent.getAbsenceDates().add(TEST_ABSENCE);
        Mockito.when(absenceService.getAbsenceByAbsenceDate(TEST_ABSENCE_DATE)).thenReturn(TEST_ABSENCE);

        Long classId = TEST_SCHOOL_CLASS_1.getId();
        Long studentId = TEST_NEW_ABSENCE_STUDENT.getId();
        Mockito.when(studentService.getStudentByIdAndClassId(studentId, classId)).thenReturn(absenceStudent);

        studentAbsenceService.deleteStudentFromAbsenceList(classId, studentId, TEST_ABSENCE_DATE);
    }

    @Test
    public void shouldDeleteStudentThrowExceptionWhenFirstParamIsNull() {

        log.info("Test deleteStudentFromAbsenceList() method throw an exception");

        Long studentId = TEST_NEW_ABSENCE_STUDENT.getId();

        Assertions.assertThrows(NullPointerException.class,
                () -> studentAbsenceService.deleteStudentFromAbsenceList(null, studentId, TEST_ABSENCE_DATE));
    }

    @Test
    public void shouldDeleteStudentThrowExceptionWhenSecondParamIsNull() {

        log.info("Test deleteStudentFromAbsenceList() method throw an exception");

        Long classId = TEST_SCHOOL_CLASS_1.getId();

        Assertions.assertThrows(NullPointerException.class,
                () -> studentAbsenceService.deleteStudentFromAbsenceList(classId, null, TEST_ABSENCE_DATE));
    }

    @Test
    public void shouldDeleteStudentThrowExceptionWhenThirdParamIsNull() {

        log.info("Test deleteStudentFromAbsenceList() method throw an exception");

        Long classId = TEST_SCHOOL_CLASS_1.getId();
        Long studentId = TEST_NEW_ABSENCE_STUDENT.getId();

        Assertions.assertThrows(NullPointerException.class,
                () -> studentAbsenceService.deleteStudentFromAbsenceList(classId, studentId, null));
    }

    @Test
    public void shouldDeleteStudentThrowExceptionWhenFirstParamIsWrong() {

        log.info("Test deleteStudentFromAbsenceList() method throw an exception");

        Long studentId = TEST_NEW_ABSENCE_STUDENT.getId();

        Assertions.assertThrows(NullPointerException.class,
                () -> studentAbsenceService.deleteStudentFromAbsenceList(WRONG_ID, studentId, TEST_ABSENCE_DATE));
    }

    @Test
    public void shouldDeleteStudentThrowExceptionWhenSecondParamIsWrong() {

        log.info("Test deleteStudentFromAbsenceList() method throw an exception");

        Long classId = TEST_SCHOOL_CLASS_1.getId();

        Assertions.assertThrows(NullPointerException.class,
                () -> studentAbsenceService.deleteStudentFromAbsenceList(classId, WRONG_ID, TEST_ABSENCE_DATE));
    }

    @Test
    public void shouldDeleteStudentThrowExceptionWhenThirdParamIsWrong() {

        log.info("Test deleteStudentFromAbsenceList() method throw an exception");

        Long classId = TEST_SCHOOL_CLASS_1.getId();
        Long studentId = TEST_NEW_ABSENCE_STUDENT.getId();

        Assertions.assertThrows(NullPointerException.class,
                () -> studentAbsenceService.deleteStudentFromAbsenceList(classId, studentId, WRONG_ABSENCE_DATE));
    }


}