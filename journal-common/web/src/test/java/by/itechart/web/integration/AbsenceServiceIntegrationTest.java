package by.itechart.web.integration;


import by.itechart.model.Absence;
import by.itechart.model.exception.AbsenceNotFoundException;
import by.itechart.service.absence.AbsenceService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;

import static by.itechart.web.data.AbsenceTestData.*;
import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@SpringBootTest
@ExtendWith(SpringExtension.class)
@Sql(scripts = { "/db/student/populate.sql" })
public class AbsenceServiceIntegrationTest {

    @Autowired
    private AbsenceService absenceService;

    @Test
    public void shouldReturnAbsenceObjectByIdProperly() throws Throwable {

        log.info("Test getAbsenceById() method");

        Absence absenceById = absenceService.getAbsenceById(10000L);

        assertThat(absenceById)
                                .usingRecursiveComparison()
                                .ignoringFields("students")
                                .isEqualTo(TEST_ABSENCE_1);
    }

    @Test
    public void shouldReturnAbsenceObjectByAbsenceDateProperly() throws Throwable {

        log.info("Test getAbsenceByAbsenceDate() method");

        Absence absenceByAbsenceDate = absenceService.getAbsenceByAbsenceDate(LocalDate.of(2021, 07, 28));

        assertThat(absenceByAbsenceDate)
                                        .usingRecursiveComparison()
                                        .ignoringFields("students")
                                        .isEqualTo(TEST_ABSENCE_2);
    }

    @Test
    public void shouldCreateAbsenceProperly() {

        log.info("Test createAbsence() method");

        Absence storedAbsence = absenceService.createAbsence(TEST_SAVE_ABSENCE);

        assertThat(storedAbsence)
                                .usingRecursiveComparison()
                                .ignoringFields("students")
                                .isEqualTo(TEST_SAVE_ABSENCE);
    }

    @Test
    public void shouldUpdateAbsenceProperly() throws Throwable {

        log.info("Test updateAbsence() method");

        Long absenceId = TEST_ABSENCE_1.getId();

        Absence updatedAbsence = absenceService.updateAbsence(absenceId, TEST_UPDATE_ABSENCE);

        assertThat(updatedAbsence)
                                .usingRecursiveComparison()
                                .ignoringFields("students")
                                .isEqualTo(TEST_UPDATE_ABSENCE);
    }

    @Test
    public void shouldDeleteAbsenceByIdProperly() {

        log.info("Test deleteAbsenceById() method");

        absenceService.deleteAbsenceById(TEST_ABSENCE_1.getId());

        Assertions.assertThrows(AbsenceNotFoundException.class,
                                        () -> absenceService.getAbsenceById(TEST_ABSENCE_1.getId()));
    }

    @Test
    public void shouldDeleteAbsenceByAbsenceDateProperly() {

        log.info("Test deleteAbsenceByAbsenceDate() method");

        absenceService.deleteAbsenceByAbsenceDate(TEST_ABSENCE_1.getAbsenceDate());

        Assertions.assertThrows(AbsenceNotFoundException.class,
                                        () -> absenceService.getAbsenceByAbsenceDate(TEST_ABSENCE_1.getAbsenceDate()));
    }
}
