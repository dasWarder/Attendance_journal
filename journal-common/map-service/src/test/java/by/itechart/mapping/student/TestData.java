package by.itechart.mapping.student;

import by.itechart.mapping.dto.StudentDto;
import by.itechart.mapping.dto.StudentDtoId;
import by.itechart.model.SchoolClass;
import by.itechart.model.Student;

public class TestData {

    public static final Long WRONG_ID = 100L;

    public static final StudentDto TEST_DTO_1 = new StudentDto("Alex", "Petrov");

    public static final StudentDto TEST_DTO_2 = new StudentDto("Petr", "Alexandrov");

    public static final StudentDtoId TEST_DTO_ID_1 = new StudentDtoId(2L, "Alex", "Petrov");

    public static final StudentDtoId TEST_DTO_ID_2 = new StudentDtoId(3L, "Petr", "Alexandrov");

    public static final SchoolClass TEST_SCHOOL_CLASS_1 = SchoolClass.builder()
                                                                                .id(1L)
                                                                                .name("1A")
                                                                                .build();

    public static final Student TEST_STUDENT_1 = Student.builder()
                                                                    .id(TEST_DTO_ID_1.getId())
                                                                    .name(TEST_DTO_1.getName())
                                                                    .surname(TEST_DTO_1.getSurname())
                                                                    .schoolClass(TEST_SCHOOL_CLASS_1)
                                                                    .build();

    public static final Student TEST_STUDENT_2 = Student.builder()
                                                                    .id(TEST_DTO_ID_2.getId())
                                                                    .name(TEST_DTO_2.getName())
                                                                    .surname(TEST_DTO_2.getSurname())
                                                                    .schoolClass(TEST_SCHOOL_CLASS_1)
                                                                    .build();
}
