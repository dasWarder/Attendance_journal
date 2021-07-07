package by.itechart.web.data;

import by.itechart.model.SchoolClass;

public class SchoolClassTestData {

    public static final SchoolClass TEST_SCHOOL_CLASS_1 = new SchoolClass(12L, "1А");

    public static final SchoolClass TEST_SAVE_SCHOOL_CLASS = new SchoolClass(1L, "12Б");

    public static final SchoolClass TEST_UPDATE_SCHOOL_CLASS = new SchoolClass(TEST_SCHOOL_CLASS_1.getId(), "UPDATED");
}
