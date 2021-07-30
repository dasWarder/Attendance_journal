package by.itechart.web.integration;

import by.itechart.model.StudentDetails;
import by.itechart.model.exception.StudentDetailsNotFoundException;
import by.itechart.service.studentDetails.StudentDetailsService;
import by.itechart.web.AbstractContextTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static by.itechart.web.data.SchoolClassTestData.TEST_SCHOOL_CLASS_1;
import static by.itechart.web.data.StudentDetailsTestData.*;
import static by.itechart.web.data.StudentTestData.TEST_STUDENT_2;
import static by.itechart.web.data.StudentTestData.TEST_STUDENT_3;
import static org.assertj.core.api.Assertions.assertThat;


@Slf4j
public class StudentDetailsServiceIntegrationContextTest extends AbstractContextTest {

    @Autowired
    private StudentDetailsService detailsService;

    @Test
    public void shouldSaveStudentDetailsProperly() throws Throwable {

        log.info("Test saveStudentDetails() method");

        Long classId = TEST_SCHOOL_CLASS_1.getId();
        Long studentId = TEST_STUDENT_2.getId();
        StudentDetails testStoreDetails = TEST_SAVE_DETAILS;

        StudentDetails studentDetails = detailsService.saveStudentDetails(classId, studentId, testStoreDetails);

        Assertions.assertNotNull(studentDetails);

        assertThat(studentDetails)
                                .usingRecursiveComparison()
                                .ignoringFields("student")
                                .isEqualTo(testStoreDetails);
    }

    @Test
    public void shouldUpdateStudentDetailsProperly() throws Throwable {

        log.info("Test updateStudentDetails() method");

        Long classId = TEST_SCHOOL_CLASS_1.getId();
        Long studentId = TEST_STUDENT_2.getId();
        StudentDetails testUpdateDetails = TEST_UPDATE_DETAILS;

        StudentDetails updatedDetails = detailsService.updateStudentDetails(classId, studentId, testUpdateDetails);
        testUpdateDetails.setId(updatedDetails.getId());

        Assertions.assertNotNull(updatedDetails);
        assertThat(updatedDetails)
                                    .usingRecursiveComparison()
                                    .ignoringFields("student")
                                    .isEqualTo(testUpdateDetails);
    }

    @Test
    public void shouldGetStudentDetailsByClassIdAndStudentIdProperly() throws Throwable {

        log.info("Test getStudentByClassIdAndStudentId() method");

        Long classId = TEST_SCHOOL_CLASS_1.getId();
        Long studentId = TEST_STUDENT_3.getId();

        StudentDetails studentDetailsById = detailsService.getStudentDetailsBySchoolClassIdAndStudentId(classId, studentId);

        Assertions.assertNotNull(studentDetailsById);
        assertThat(studentDetailsById)
                                    .usingRecursiveComparison()
                                    .ignoringFields("student")
                                    .isEqualTo(TEST_DETAILS_2);
    }

    @Test
    public void shouldDeleteStudentDetailsProperly() {

        log.info("Test deleteStudentDetails() method");

        Long classId = TEST_SCHOOL_CLASS_1.getId();
        Long studentId = TEST_STUDENT_3.getId();

        detailsService.deleteStudentDetailsBySchoolClassIdAndStudentId(classId, studentId);

        Assertions.assertThrows(StudentDetailsNotFoundException.class,
                            () -> detailsService.getStudentDetailsBySchoolClassIdAndStudentId(classId, studentId));
    }
}
