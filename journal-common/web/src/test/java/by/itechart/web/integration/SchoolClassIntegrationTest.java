package by.itechart.web.integration;

import by.itechart.model.SchoolClass;
import by.itechart.model.exception.SchoolClassNotFound;
import by.itechart.service.schoolClass.SchoolClassService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static by.itechart.web.data.GeneralTestData.LOGGED_USERNAME;
import static by.itechart.web.data.GeneralTestData.PAGE_PARAM;
import static by.itechart.web.data.SchoolClassTestData.*;
import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@SpringBootTest
@ExtendWith(SpringExtension.class)
@Sql(scripts = { "/db/student/populate.sql" })
public class SchoolClassIntegrationTest {

    @Autowired
    private SchoolClassService classService;


    @Test
    public void shouldSaveANewSchoolClassProperly() {

        log.info("Test saveSchoolClass() method");

        SchoolClass schoolClassToStore = TEST_SAVE_SCHOOL_CLASS;
        SchoolClass storedSchoolClass = classService.saveSchoolClass(schoolClassToStore);
        schoolClassToStore.setId(
                                 storedSchoolClass.getId());

        assertThat(schoolClassToStore)
                                    .usingRecursiveComparison()
                                    .isEqualTo(storedSchoolClass);
    }

    @Test
    public void shouldGetSchoolClassByIdProperly() throws Throwable {

        log.info("Test getSchoolClassById() method");

        Long classId = TEST_SCHOOL_CLASS_1.getId();

        SchoolClass schoolClassById = classService.getSchoolClassById(classId, LOGGED_USERNAME);

        assertThat(TEST_SCHOOL_CLASS_1)
                                        .usingRecursiveComparison()
                                        .ignoringFields("students", "user")
                                        .isEqualTo(schoolClassById);
    }

    @Test
    public void shouldGetSchoolClassByNameProperly() throws Throwable {

        log.info("Test getSchoolClassByName() method");

        String className = TEST_SCHOOL_CLASS_1.getName();

        SchoolClass schoolClassById = classService.getSchoolClassByName(className, LOGGED_USERNAME);

        assertThat(TEST_SCHOOL_CLASS_1)
                .usingRecursiveComparison()
                .ignoringFields("students", "user")
                .isEqualTo(schoolClassById);
    }

    @Test
    @Sql(scripts = "/db/student/populate.sql")
    public void shouldUpdateSchoolClassProperly() throws Throwable {

        log.info("Test updateSchoolClass() method");

        Long classId = TEST_SCHOOL_CLASS_1.getId();
        SchoolClass updateSchoolClass = TEST_UPDATE_SCHOOL_CLASS;

        SchoolClass updatedSchoolClass = classService.updateSchoolClass(classId, updateSchoolClass, LOGGED_USERNAME);

        assertThat(updatedSchoolClass)
                                    .usingRecursiveComparison()
                                    .ignoringFields("students", "user")
                                    .isEqualTo(TEST_UPDATE_SCHOOL_CLASS);
    }

    @Test
    public void shouldDeleteSchoolClassByIdProperly() {

        log.info("Test deleteSchoolClassById() method");

        SchoolClass storedSchoolClass = classService.saveSchoolClass(TEST_SAVE_SCHOOL_CLASS);

        Assertions.assertNotNull(storedSchoolClass);

        Long classId = storedSchoolClass.getId();
        classService.deleteSchoolClassById(classId, LOGGED_USERNAME);

        Assertions.assertThrows(SchoolClassNotFound.class,
                                                         () -> classService.getSchoolClassById(classId, LOGGED_USERNAME));
    }

    @Test
    public void shouldDeleteSchoolClassByNameProperly() {

        log.info("Test deleteSchoolClassById() method");

        SchoolClass storedSchoolClass = classService.saveSchoolClass(TEST_SAVE_SCHOOL_CLASS);

        Assertions.assertNotNull(storedSchoolClass);

        String className= storedSchoolClass.getName();
        classService.deleteSchoolClassByName(className, LOGGED_USERNAME);

        Assertions.assertThrows(SchoolClassNotFound.class,
                () -> classService.getSchoolClassByName(className, LOGGED_USERNAME));
    }

    @Test
    public void shouldGetAllSchoolClassesProperly() {

        log.info("Test getAllSchoolClasses() method");

        List<SchoolClass> testSchoolClasses = List.of(TEST_SCHOOL_CLASS_1);

        List<SchoolClass> allSchoolClasses = classService.getAllSchoolClasses(LOGGED_USERNAME, PAGE_PARAM);

        assertThat(allSchoolClasses)
                                    .usingRecursiveComparison()
                                    .ignoringFields("students", "user")
                                    .isEqualTo(testSchoolClasses);
    }
}
