package by.itechart.web.data;

import by.itechart.mapping.dto.student.StudentDto;
import by.itechart.mapping.dto.student.StudentDtoId;
import by.itechart.model.SchoolClass;
import by.itechart.model.Student;

public class StudentTestData {

    public static final SchoolClass TEST_SCHOOL_CLASS_1 = new SchoolClass(12L, "1A");

    public static final Student TEST_STUDENT_1 = new Student(30L, "Stored", "Stored", TEST_SCHOOL_CLASS_1);

    public static final Student TEST_STUDENT_2 = new Student(6L, "Alex", "Petrov", TEST_SCHOOL_CLASS_1);

    public static final Student TEST_STUDENT_3 = new Student(7L, "Jack", "Sparrow", TEST_SCHOOL_CLASS_1);

    public static final Student TEST_STUDENT_4 = new Student(8L, "David", "Blane", TEST_SCHOOL_CLASS_1);

    public static final Student UPDATE_STUDENT = new Student(6L, "Updated", "Updated", TEST_SCHOOL_CLASS_1);

    public static final StudentDto TEST_STORE_STUDENT_1 = new StudentDto( "Saved", "Saved");

    public static final StudentDtoId TEST_UPDATE_STUDENT_1 = new StudentDtoId(TEST_STUDENT_2.getId(), "UPDATED", "UPDATED");

    public static final String REMOVE_MESSAGE = "The student with ID = %d for a school class with ID = %d was successfully removed";
}
