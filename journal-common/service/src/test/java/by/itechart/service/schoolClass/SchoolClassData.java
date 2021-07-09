package by.itechart.service.schoolClass;

import by.itechart.model.SchoolClass;

import static by.itechart.service.TestData.TEST_SCHOOL_CLASS_1;

public class SchoolClassData {

    public static final SchoolClass TEST_SCHOOL_CLASS_3 = new SchoolClass(3L, "1В");

    public static final SchoolClass TEST_UPDATE_SCHOOL_CLASS = new SchoolClass(null, "UPDATED");

    public static final SchoolClass TEST_UPDATED_SCHOOL_CLASS = new SchoolClass(TEST_SCHOOL_CLASS_1.getId(), "UPDATED");

}
