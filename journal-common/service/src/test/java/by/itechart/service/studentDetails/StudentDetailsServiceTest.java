package by.itechart.service.studentDetails;

import by.itechart.model.SchoolClass;
import by.itechart.model.Student;
import by.itechart.model.StudentDetails;
import by.itechart.model.exception.StudentDetailsNotFoundException;
import by.itechart.repository.StudentDetailsRepository;
import by.itechart.service.student.StudentService;
import by.itechart.service.student.StudentServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static by.itechart.service.TestData.TEST_SCHOOL_CLASS_1;
import static by.itechart.service.TestData.WRONG_ID;
import static by.itechart.service.student.StudentTestData.TEST_STUDENT_1;
import static by.itechart.service.studentDetails.DetailsTestData.TEST_DETAILS_1;
import static by.itechart.service.studentDetails.DetailsTestData.TEST_UPDATE_DETAILS;
import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class StudentDetailsServiceTest {

    private final StudentService studentService = Mockito.mock(StudentServiceImpl.class);


    private final StudentDetailsRepository detailsRepository = Mockito.mock(StudentDetailsRepository.class);

    private final StudentDetailsService detailsService = new StudentDetailsServiceImpl(detailsRepository, studentService);

    @Test
    public void shouldSaveStudentDetailsMethodWorksProperly() throws Throwable {

        log.info("Test saveStudentDetails() method");

        Student testStudent = TEST_STUDENT_1;
        SchoolClass testSchoolClass = TEST_SCHOOL_CLASS_1;

        Mockito.when(studentService.getStudentByIdAndClassId(testStudent.getId(), testSchoolClass.getId()))
                                                                                    .thenReturn(testStudent);
        Mockito.when(detailsRepository.save(TEST_DETAILS_1))
                                        .thenReturn(TEST_DETAILS_1);
        StudentDetails storedDetails = detailsService.saveStudentDetails(testSchoolClass.getId(),
                                                                         testStudent.getId(), TEST_DETAILS_1);

        Assertions.assertNotNull(storedDetails);

        assertThat(storedDetails)
                                .usingRecursiveComparison()
                                .isEqualTo(TEST_DETAILS_1);
    }

    @Test
    public void shouldThrowExceptionSaveStudentDetailsWhenFirstParamIsNull() {

        log.info("Test saveStudentDetails() method must throw exception when the first param is null");

        Assertions.assertThrows(NullPointerException.class,
                    () -> detailsService.saveStudentDetails(null, TEST_STUDENT_1.getId(), TEST_DETAILS_1));
    }

    @Test
    public void shouldThrowExceptionSaveStudentDetailsWhenSecondParamIsNull() {

        log.info("Test saveStudentDetails() method must throw exception when the second param is null");

        Assertions.assertThrows(NullPointerException.class,
                () -> detailsService.saveStudentDetails(TEST_SCHOOL_CLASS_1.getId(), null, TEST_DETAILS_1));
    }

    @Test
    public void shouldThrowExceptionSaveStudentDetailsWhenThirdParamIsNull() {

        log.info("Test saveStudentDetails() method must throw exception when the third param is null");

        Assertions.assertThrows(NullPointerException.class,
                () -> detailsService.saveStudentDetails(TEST_SCHOOL_CLASS_1.getId(), TEST_STUDENT_1.getId(), null));
    }

    @Test
    public void shouldReturnStudentDetailsByClassIdAndStudentIdProperly() throws Throwable {

        log.info("Test getStudentDetailsByClassIdAndStudentId() method");

        Long classId = TEST_SCHOOL_CLASS_1.getId();
        Long studentId = TEST_STUDENT_1.getId();

        Mockito.when(detailsRepository.getStudentDetailsByStudentSchoolClassIdAndStudentId(classId, studentId))
                                                                                        .thenReturn(Optional.of(TEST_DETAILS_1));
        StudentDetails response = detailsService.getStudentDetailsBySchoolClassIdAndStudentId(classId, studentId);

        Assertions.assertNotNull(response);

        assertThat(response)
                            .usingRecursiveComparison()
                            .isEqualTo(TEST_DETAILS_1);
    }

    @Test
    public void shouldThrowExceptionGetStudentDetailsByClassIdAndStudentIdWhenFirstParamIsNull() {

        log.info("Test getStudentDetailsByClassIdAndStudentId() method throw an exception when the first param is null");

        Long studentId = TEST_STUDENT_1.getId();

        Assertions.assertThrows(NullPointerException.class,
                                () -> detailsService.getStudentDetailsBySchoolClassIdAndStudentId(null, studentId));
    }

    @Test
    public void shouldThrowExceptionGetStudentDetailsByClassIdAndStudentIdWhenSecondParamIsNull() {

        log.info("Test getStudentDetailsByClassIdAndStudentId() method throw an exception when the second param is null");

        Long classId = TEST_SCHOOL_CLASS_1.getId();

        Assertions.assertThrows(NullPointerException.class,
                () -> detailsService.getStudentDetailsBySchoolClassIdAndStudentId(classId, null));
    }

    @Test
    public void shouldThrowExceptionGetStudentDetailsByClassIdAndStudentIdWhenParamsWrong() {

        log.info("Test getStudentDetailsByClassIdAndStudentId() method throw an exception when the first param is wrong");

        Long classId = WRONG_ID;
        Long studentId = WRONG_ID;

        Mockito.when(detailsRepository.getStudentDetailsByStudentSchoolClassIdAndStudentId(classId, studentId))
                                                                                .thenReturn(Optional.ofNullable(null));
        Assertions.assertThrows(StudentDetailsNotFoundException.class,
                                         () -> detailsService.getStudentDetailsBySchoolClassIdAndStudentId(classId, studentId));
    }

    @Test
    public void shouldDeleteStudentDetailsByClassIdAndStudentIdProperly() {

        log.info("Test deleteStudentDetailsByClassIdAndStudentId() method");

        Long classId = TEST_SCHOOL_CLASS_1.getId();
        Long studentId = TEST_STUDENT_1.getId();

        detailsService.deleteStudentDetailsBySchoolClassIdAndStudentId(classId, studentId);
    }

    @Test
    public void shouldThrowExceptionDeleteStudentDetailsByClassIdAndStudentIdWhenFirstParamNull() {

        log.info("Test deleteStudentDetailsByClassIdAndStudentId() method throw an exception when the first param is null");

        Long studentId = TEST_STUDENT_1.getId();

        Assertions.assertThrows(NullPointerException.class,
                                () ->  detailsService.deleteStudentDetailsBySchoolClassIdAndStudentId(null, studentId));
    }

    @Test
    public void shouldThrowExceptionDeleteStudentDetailsByClassIdAndStudentIdWhenSecondParamNull() {

        log.info("Test deleteStudentDetailsByClassIdAndStudentId() method throw an exception when the second param is null");

        Long classId = TEST_SCHOOL_CLASS_1.getId();

        Assertions.assertThrows(NullPointerException.class,
                () ->  detailsService.deleteStudentDetailsBySchoolClassIdAndStudentId(classId, null));
    }

    @Test
    public void shouldUpdateStudentDetailsProperly() throws Throwable {

        log.info("Test updateStudentDetails() method");

        Long classId = TEST_SCHOOL_CLASS_1.getId();
        Long studentId = TEST_STUDENT_1.getId();
        StudentDetails updateDetails = TEST_UPDATE_DETAILS;

        Mockito.when(detailsRepository.getStudentDetailsByStudentSchoolClassIdAndStudentId(classId, studentId))
                                                                            .thenReturn(Optional.of(TEST_DETAILS_1));
        Mockito.when(detailsRepository.save(updateDetails)).thenReturn(TEST_UPDATE_DETAILS);

        StudentDetails updatedDetails = detailsService.updateStudentDetails(classId, studentId, TEST_UPDATE_DETAILS);

        Assertions.assertNotNull(updatedDetails);

        assertThat(updatedDetails)
                                    .usingRecursiveComparison()
                                    .isEqualTo(TEST_UPDATE_DETAILS);
    }

    @Test
    public void shouldThrowExceptionUpdateStudentDetailsWhenFirstParamIsNull() {

        log.info("Test updateStudentDetails() method throw an exception when the first param is null");

        Long studentId = TEST_STUDENT_1.getId();

        Assertions.assertThrows(NullPointerException.class,
                                () -> detailsService.updateStudentDetails(null, studentId, TEST_UPDATE_DETAILS));
    }

    @Test
    public void shouldThrowExceptionUpdateStudentDetailsWhenSecondParamIsNull() {

        log.info("Test updateStudentDetails() method throw an exception when the second param is null");

        Long classId = TEST_SCHOOL_CLASS_1.getId();

        Assertions.assertThrows(NullPointerException.class,
                () -> detailsService.updateStudentDetails(classId, null, TEST_UPDATE_DETAILS));
    }

    @Test
    public void shouldThrowExceptionUpdateStudentDetailsWhenParamsWrong() {

        log.info("Test updateStudentDetails() method throw an exception when params are wrong");

        Long classId = WRONG_ID;
        Long studentId = WRONG_ID;

        Mockito.when(detailsRepository.getStudentDetailsByStudentSchoolClassIdAndStudentId(classId, studentId))
                                                                                    .thenReturn(Optional.ofNullable(null));

        Assertions.assertThrows(StudentDetailsNotFoundException.class,
                () -> detailsService.updateStudentDetails(classId, studentId, TEST_UPDATE_DETAILS));
    }

}