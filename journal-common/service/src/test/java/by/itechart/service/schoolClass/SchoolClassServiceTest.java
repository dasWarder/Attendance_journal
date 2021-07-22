package by.itechart.service.schoolClass;

import by.itechart.model.SchoolClass;
import by.itechart.model.exception.SchoolClassNotFound;
import by.itechart.repository.SchoolClassRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

import static by.itechart.service.TestData.*;
import static by.itechart.service.schoolClass.SchoolClassData.*;
import static org.assertj.core.api.Assertions.assertThat;


@Slf4j
class SchoolClassServiceTest {

    private SchoolClassRepository schoolClassRepository = Mockito.mock(SchoolClassRepository.class);

    private SchoolClassService schoolClassService = new SchoolClassServiceImpl(schoolClassRepository);


    @Test
    public void shouldSaveSchoolClassMethodWorksProperly() {

        log.info("Test saveSchoolClass() method");
        Mockito.when(schoolClassRepository.save(TEST_SCHOOL_CLASS_1))
                                                                    .thenReturn(TEST_SCHOOL_CLASS_1);

        SchoolClass schoolClass = schoolClassService.saveSchoolClass(TEST_SCHOOL_CLASS_1);

        assertThat(schoolClass)
                            .usingRecursiveComparison()
                            .isEqualTo(TEST_SCHOOL_CLASS_1);
    }

    @Test
    public void shouldThrowExceptionWhenSaveNullableSchoolClass() {

        log.info("Test saveSchoolClass() method throw exception when a school class object is NULL");

        Assertions.assertThrows(NullPointerException.class,
                                                     () -> schoolClassService.saveSchoolClass(null));
    }

    @Test
    public void shouldGetSchoolClassByIdMethodWorksProperly() throws Throwable {

        log.info("Test getSchoolClassById() method");

        Long classId = TEST_SCHOOL_CLASS_1.getId();
        Mockito.when(schoolClassRepository.getSchoolClassByIdAndUser_Username(classId, LOGGED_USER))
                                                            .thenReturn(Optional.of(TEST_SCHOOL_CLASS_1));

        SchoolClass schoolClassById = schoolClassService.getSchoolClassById(classId, LOGGED_USER);

        assertThat(schoolClassById)
                                .usingRecursiveComparison()
                                .isEqualTo(TEST_SCHOOL_CLASS_1);
    }

    @Test
    public void shouldThrowExceptionWhenGetSchoolClassByIdWithNullableId() {

        log.info("Test getSchoolById() method throw an exception when a school class id is NULL");

        Assertions.assertThrows(NullPointerException.class,
                                                    () -> schoolClassService.getSchoolClassById(null, LOGGED_USER));
    }

    @Test
    public void shouldThrowExceptionWhenGetSchoolClassByIdWithWrongId() throws Throwable {

        log.info("Test getSchoolClassById() throw an exception when a school class id is wrong");
        Mockito.when(schoolClassRepository.getSchoolClassByIdAndUser_Username(WRONG_ID, LOGGED_USER))
                                                                .thenReturn(Optional.ofNullable(null));

        Assertions.assertThrows(SchoolClassNotFound.class,
                                                    () -> schoolClassService.getSchoolClassById(WRONG_ID, LOGGED_USER));
    }

    @Test
    public void shouldGetSchoolClassByNameMethodWorksProperly() throws Throwable {

        log.info("Test getSchoolClassByName() method");

        String className = TEST_SCHOOL_CLASS_1.getName();
        Mockito.when(schoolClassRepository.getSchoolClassByNameAndUser_Username(className, LOGGED_USER))
                                                                        .thenReturn(Optional.of(TEST_SCHOOL_CLASS_1));

        SchoolClass schoolClassByName = schoolClassService.getSchoolClassByName(className, LOGGED_USER);

        assertThat(schoolClassByName)
                                    .usingRecursiveComparison()
                                    .isEqualTo(TEST_SCHOOL_CLASS_1);
    }

    @Test
    public void shouldThrowExceptionWhenGetSchoolClassByNameWithNullableName() {

        log.info("Test getSchoolByName() method throw an exception when a school class name is NULL");

        Assertions.assertThrows(NullPointerException.class,
                                                () -> schoolClassService.getSchoolClassByName(null, LOGGED_USER));
    }

    @Test
    public void shouldThrowExceptionWhenGetSchoolClassByNameWithWrongName() throws Throwable {

        log.info("Test getSchoolClassByName() throw an exception when a school class name is wrong");
        Mockito.when(schoolClassRepository.getSchoolClassByNameAndUser_Username(WRONG_NAME, LOGGED_USER))
                                                                            .thenReturn(Optional.ofNullable(null));

        Assertions.assertThrows(SchoolClassNotFound.class,
                                                      () -> schoolClassService.getSchoolClassByName(WRONG_NAME, LOGGED_USER));
    }


    @Test
    public void shouldGetAllSchoolClassesMethodWorksProperly() {

        log.info("Test getAllSchoolClasses() method");
        List<SchoolClass> actualSchoolClasses = List.of(
                                                    TEST_SCHOOL_CLASS_1,
                                                    TEST_SCHOOL_CLASS_2,
                                                    TEST_SCHOOL_CLASS_3);
        Mockito.when(schoolClassRepository.getSchoolClassesByUser_Username(LOGGED_USER))
                                                                                     .thenReturn(actualSchoolClasses);

        List<SchoolClass> allSchoolClasses = schoolClassService.getAllSchoolClasses(LOGGED_USER, PAGE_PARAM);

        assertThat(allSchoolClasses)
                                    .usingRecursiveComparison()
                                    .isEqualTo(actualSchoolClasses);
    }

    @Test
    public void shouldDeleteSchoolClassByIdMethodWorksProperly() {

        log.info("Test deleteSchoolClassById() method");
        Long classId = TEST_SCHOOL_CLASS_1.getId();

        schoolClassService.deleteSchoolClassById(classId, LOGGED_USER);
    }

    @Test
    public void shouldThrowExceptionWhenDeleteSchoolClassByIdWithNullableId() {

        log.info("Test deleteSchoolClassById() method throw an exception when a school class id is NULL");

        Assertions.assertThrows(NullPointerException.class,
                                                        () -> schoolClassService.deleteSchoolClassById(null, LOGGED_USER));
    }

    @Test
    public void shouldDeleteSchoolClassByNameMethodWorksProperly() {

        log.info("Test deleteSchoolClassByName() method");
        String className = TEST_SCHOOL_CLASS_1.getName();

        schoolClassService.deleteSchoolClassByName(className, LOGGED_USER);
    }

    @Test
    public void shouldThrowExceptionWhenDeleteSchoolClassByNameWithNullableName() {

        log.info("Test deleteSchoolClassByName() method throw an exception when a school class name is NULL");

        Assertions.assertThrows(NullPointerException.class,
                                                    () -> schoolClassService.deleteSchoolClassByName(null, LOGGED_USER));
    }


    @Test
    public void shouldUpdateSchoolClassMethodWorksProperly() throws Throwable {

        log.info("Test updateSchoolClass() method");

        Long classId = TEST_SCHOOL_CLASS_1.getId();
        Mockito.when(schoolClassRepository.getSchoolClassByIdAndUser_Username(classId, LOGGED_USER))
                                                            .thenReturn(Optional.of(TEST_SCHOOL_CLASS_1));
        SchoolClass updateSchoolClass = TEST_UPDATE_SCHOOL_CLASS;
        Mockito.when(schoolClassRepository.save(updateSchoolClass))
                                                                            .thenReturn(updateSchoolClass);

        SchoolClass schoolClass = schoolClassService.updateSchoolClass(classId, updateSchoolClass, LOGGED_USER);

        assertThat(schoolClass)
                                .usingRecursiveComparison()
                                .isEqualTo(TEST_UPDATED_SCHOOL_CLASS);

    }

    @Test
    public void shouldThrowExceptionWhenUpdateSchoolClassWithNullableId() {

        log.info("Test updateSchoolClass() method throw an exception when a school class id is NULL");
        SchoolClass updateSchoolClass = TEST_UPDATE_SCHOOL_CLASS;

        Assertions.assertThrows(NullPointerException.class,
                                        () -> schoolClassService.updateSchoolClass(null, updateSchoolClass, LOGGED_USER));
    }

    @Test
    public void shouldThrowExceptionWhenUpdateSchoolClassWithNullableUpdateSchoolClassObject() {

        log.info("Test updateSchoolClass() method throw an exception when a update school class object is NULL");
        Long classId = TEST_SCHOOL_CLASS_1.getId();

        Assertions.assertThrows(NullPointerException.class,
                                         () -> schoolClassService.updateSchoolClass(classId, null, LOGGED_USER));
    }


    @Test
    public void shouldThrowExceptionWhenUpdateSchoolClassWithWrongId() {

        log.info("Test updateSchoolClass() method throw an exception when a school class id is wrong");

        Long classId = WRONG_ID;
        SchoolClass updateSchoolClass = TEST_UPDATE_SCHOOL_CLASS;
        Mockito.when(schoolClassRepository.findById(classId))
                                                        .thenReturn(Optional.ofNullable(null));

        Assertions.assertThrows(SchoolClassNotFound.class,
                                        () -> schoolClassService.updateSchoolClass(classId, updateSchoolClass, LOGGED_USER));
    }


}