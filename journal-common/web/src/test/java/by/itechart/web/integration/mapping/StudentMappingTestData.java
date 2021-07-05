package by.itechart.web.integration.mapping;

import by.itechart.mapping.dto.StudentDto;
import by.itechart.model.SchoolClass;
import by.itechart.model.Student;

public class StudentMappingTestData {

    public static final SchoolClass TEST_SCHOOL_CLASS_1 = new SchoolClass(1L,"1А");

    public static final StudentDto TEST_DTO_1 = new StudentDto("Alex", "Petrov");

    public static final Student TEST_STUDENT_1 = new Student(TEST_DTO_1.getName(), TEST_DTO_1.getSurname(), TEST_SCHOOL_CLASS_1);
}
