package by.itechart.service.studentDetails;

import by.itechart.model.StudentDetails;

public class DetailsTestData {

    public static final StudentDetails TEST_DETAILS_1 = new StudentDetails(1000L, "Oleg Ivanov",
                                "Irina Ivanova", "oleg@gmail.com", "+79113880711",
                                        "Nothing to say", null);

    public static final StudentDetails TEST_UPDATE_DETAILS = new StudentDetails("Updated", "Updated",
                                                "updated@gmail.com", "+12345678910", "Updated");

}
