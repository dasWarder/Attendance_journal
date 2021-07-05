package by.itechart.mapping.student;

import by.itechart.mapping.dto.StudentDto;
import by.itechart.mapping.exception.SchoolClassNotFound;
import by.itechart.model.Student;
import by.itechart.repository.SchoolClassRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

import static by.itechart.mapping.student.TestData.*;
import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class StudentMappingTest {


    private SchoolClassRepository classRepository = Mockito.mock(SchoolClassRepository.class);

    private StudentMapping studentMapping = new StudentMappingImpl(classRepository);


    @Test
    public void shouldMapFromStudentDtoToStudentProperly() throws Throwable {

        log.info("Mapping a Student Dto to Student");

        Mockito.when(classRepository.findById(TEST_SCHOOL_CLASS_1.getId()))
                                                                            .thenReturn(Optional.of(TEST_SCHOOL_CLASS_1));

        Student student = studentMapping.fromStudentDtoToStudent(TEST_DTO_1, TEST_SCHOOL_CLASS_1.getId());

        assertThat(student)
                            .usingRecursiveComparison()
                            .ignoringFields("id")
                            .isEqualTo(TEST_STUDENT_1);
    }

    @Test
    public void shouldMapFromStudentToStudentDtoProperly() {

        log.info("Mapping a student to a student dto");

        StudentDto dto = studentMapping.fromStudentToStudentDto(TEST_STUDENT_1);

        assertThat(dto)
                        .usingRecursiveComparison()
                        .isEqualTo(TEST_DTO_1);

    }

    @Test
    public void shouldMapProperlyFromStudentListToStudentDtoList() {

        log.info("Mapping from a list of student to a list of student dto");
        List<Student> studentList = List.of(TEST_STUDENT_1, TEST_STUDENT_2);
        List<StudentDto> expectedList = List.of(TEST_DTO_1, TEST_DTO_2);

        List<StudentDto> actualList = studentMapping.fromStudentListToStudentDtoList(studentList);

        assertThat(actualList)
                                .usingRecursiveComparison()
                                .isEqualTo(expectedList);

    }

    @Test
    public void shouldThrowExceptionWhenSchoolClassWithIdDoesNotExist() {

        log.info("Mapping from a student dto to a student with wrong ID");

        Mockito.when(classRepository.findById(WRONG_ID)).thenReturn(Optional.ofNullable(null));

        Assertions
                .assertThrows(SchoolClassNotFound.class,
                                                        () -> studentMapping.fromStudentDtoToStudent(
                                                                                                        TEST_DTO_1,
                                                                                                        WRONG_ID));
    }

}