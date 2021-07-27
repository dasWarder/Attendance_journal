package by.itechart.web.data;

import by.itechart.model.Absence;

import java.time.LocalDate;
import java.util.HashSet;

public class AbsenceTestData {


    public static final Absence TEST_ABSENCE_1 = new Absence(10000L, LocalDate.of(2021, 07, 27), new HashSet<>());

    public static final Absence TEST_ABSENCE_2 = new Absence(10001L, LocalDate.of(2021, 07, 28), new HashSet<>());

    public static final Absence TEST_SAVE_ABSENCE = new Absence(10002L, LocalDate.of(2021, 07, 29), new HashSet<>());

    public static final Absence TEST_UPDATE_ABSENCE = new Absence(TEST_ABSENCE_1.getId(), LocalDate.of(2007, 01, 01), new HashSet<>());
}
