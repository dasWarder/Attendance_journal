package by.itechart.service.student;

import by.itechart.model.SchoolClass;
import by.itechart.model.Student;

public class StudentTestData {

    public static final Long WRONG_ID = 100L;

    public static final SchoolClass TEST_SCHOOL_CLASS_1 = SchoolClass.builder()
                                                                                .id(1L)
                                                                                .name("1А")
                                                                                .build();

    public static final SchoolClass TEST_SCHOOL_CLASS_2 = SchoolClass.builder()
                                                                                .id(2L)
                                                                                .name("1Б")
                                                                                .build();

    public static final Student TEST_STUDENT_1 = Student.builder()
                                                                .id(3L)
                                                                .name("Jack")
                                                                .surname("Black")
                                                                .schoolClass(TEST_SCHOOL_CLASS_1)
                                                                .build();

    public static final Student TEST_STUDENT_2 = Student.builder()
                                                                .id(4L)
                                                                .name("Petv")
                                                                .surname("Vlasov")
                                                                .schoolClass(TEST_SCHOOL_CLASS_1)
                                                                .build();

    public static final Student TEST_STUDENT_3 = Student.builder()
                                                                .id(5L)
                                                                .name("Alex")
                                                                .surname("Boldwin")
                                                                .schoolClass(TEST_SCHOOL_CLASS_2)
                                                                .build();

    public static final Student UPDATED_STUDENT_1 = Student.builder()
                                                                .id(3L)
                                                                .name("Updated")
                                                                .surname("updated")
                                                                .schoolClass(TEST_SCHOOL_CLASS_1)
                                                                .build();
}
