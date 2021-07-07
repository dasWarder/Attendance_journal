package by.itechart.web.data;

import by.itechart.mapping.dto.student.StudentDto;
import by.itechart.mapping.dto.student.StudentDtoId;
import by.itechart.model.SchoolClass;
import by.itechart.model.Student;

public class StudentTestData {

    public static final SchoolClass TEST_SCHOOL_CLASS_1 = new SchoolClass(1L, "1А");

    public static final Student TEST_STUDENT_1 = new Student(null, "Stored", "Stored", TEST_SCHOOL_CLASS_1);

    public static final Student TEST_STUDENT_2 = new Student(2L, "Alex", "Petrov", TEST_SCHOOL_CLASS_1);

    public static final Student TEST_STUDENT_3 = new Student(3L, "Jack", "Sparrow", TEST_SCHOOL_CLASS_1);

    public static final Student TEST_STUDENT_4 = new Student(4L, "David", "Blane", TEST_SCHOOL_CLASS_1);

    public static final Student UPDATE_STUDENT = new Student(2L, "Updated", "Updated", TEST_SCHOOL_CLASS_1);

    public static final StudentDto TEST_STORE_STUDENT_1 = new StudentDto( "Saved", "Saved");

    public static final StudentDtoId TEST_UPDATE_STUDENT_1 = new StudentDtoId(TEST_STUDENT_2.getId(), "UPDATED", "UPDATED");

    public static final String REMOVE_MESSAGE = "The student with ID = %d for a school class with ID = %d was successfully removed";
}
