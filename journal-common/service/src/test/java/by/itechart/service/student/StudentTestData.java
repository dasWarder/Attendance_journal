package by.itechart.service.student;

import by.itechart.model.Student;

import static by.itechart.service.TestData.TEST_SCHOOL_CLASS_1;
import static by.itechart.service.TestData.TEST_SCHOOL_CLASS_2;

public class StudentTestData {


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
