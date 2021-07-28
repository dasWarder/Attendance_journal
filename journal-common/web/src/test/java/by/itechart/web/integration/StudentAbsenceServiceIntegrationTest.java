package by.itechart.web.integration;


import by.itechart.model.Student;
import by.itechart.service.attending.StudentAbsenceService;
import by.itechart.web.AbstractContextTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

import static by.itechart.web.data.SchoolClassTestData.TEST_SCHOOL_CLASS_1;
import static by.itechart.web.data.StudentTestData.TEST_STUDENT_2;
import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class StudentAbsenceServiceIntegrationTest extends AbstractContextTest {

    @Autowired
    private StudentAbsenceService studentAbsenceService;

    @Test
    public void shouldAddStudentToAbsenceListProperly() throws Throwable {

        log.info("Test addStudentToAbsenceList() method");

        Long classId = TEST_SCHOOL_CLASS_1.getId();
        Long studentId = TEST_STUDENT_2.getId();
        LocalDate absenceDate = LocalDate.of(2021, 07, 20);

        Student absenceStudent = studentAbsenceService.addStudentToAbsenceList(classId, studentId, absenceDate);

        assertThat(absenceStudent)
                                .usingRecursiveComparison()
                                .ignoringFields("absenceDates", "schoolClass")
                                .isEqualTo(TEST_STUDENT_2);
    }
}
