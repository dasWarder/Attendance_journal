package by.itechart.service.attending;

import by.itechart.model.Absence;
import by.itechart.model.Student;
import by.itechart.service.TestData;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static by.itechart.service.TestData.TEST_SCHOOL_CLASS_1;
import static by.itechart.service.student.StudentTestData.*;

public class StudentAbsenceTestData {

    public static final LocalDate WRONG_ABSENCE_DATE = LocalDate.of(2018, 12, 11);

    public static final List<Absence> TEST_ABSENCE_LIST = new ArrayList<>();

    public static final List<Student> TEST_ABSENCE_STUDENT_LIST = List.of(TEST_STUDENT_1, TEST_STUDENT_2);

    public static final LocalDate TEST_ABSENCE_DATE = LocalDate.of(2021, 07, 20);

    public static final Absence TEST_ABSENCE = new Absence(10000L, TEST_ABSENCE_DATE, new HashSet<>(TEST_ABSENCE_STUDENT_LIST));

    public static final List<Student> TEST_ALL_STUDENTS = List.of(TEST_STUDENT_1, TEST_STUDENT_2, TEST_STUDENT_3);

    public static final Student TEST_NEW_ABSENCE_STUDENT = new Student(4L, "Test", "Student", TEST_SCHOOL_CLASS_1, TEST_ABSENCE_LIST);

}
