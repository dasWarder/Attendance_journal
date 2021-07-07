package by.itechart.service.schoolClass;

import by.itechart.model.SchoolClass;

public class SchoolClassData {

    public static final Long WRONG_ID = 100L;

    public static final String WRONG_NAME = "Wrong";

    public static final SchoolClass TEST_SCHOOL_CLASS_1 = new SchoolClass(1L, "1А");

    public static final SchoolClass TEST_SCHOOL_CLASS_2 = new SchoolClass(2L, "1Б");

    public static final SchoolClass TEST_SCHOOL_CLASS_3 = new SchoolClass(3L, "1В");

    public static final SchoolClass TEST_SCHOOL_CLASS_WITHOUT_ID = new SchoolClass(null, TEST_SCHOOL_CLASS_1.getName());

    public static final SchoolClass TEST_UPDATE_SCHOOL_CLASS = new SchoolClass(null, "UPDATED");

    public static final SchoolClass TEST_UPDATED_SCHOOL_CLASS = new SchoolClass(TEST_SCHOOL_CLASS_1.getId(), "UPDATED");

}
