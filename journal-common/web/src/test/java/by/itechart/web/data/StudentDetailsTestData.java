package by.itechart.web.data;

import by.itechart.model.StudentDetails;

import static by.itechart.web.data.StudentTestData.*;

public class StudentDetailsTestData {
    /*
    (1000, 6, 'Petr Petrov', 'Olga Petrova', 'petr@gmail.com', '+79113880777', 'Just a poor boy'),
    (1001, 7, 'Unknown Pirate', '', '', '', 'PIRAAAATE!!!');
     */
    public static final StudentDetails TEST_DETAILS_1 = new StudentDetails(1000L, "Petr Petrov", "Olga Petrova",
                                                "petr@gmail.com", "+79113880777", "Just a poor boy", TEST_STUDENT_2);
    public static final StudentDetails TEST_DETAILS_2 = new StudentDetails(1001L, "Unknown Pirate", "", "",
                                                "", "PIRAAAATE!!!", TEST_STUDENT_3);

    public static final StudentDetails TEST_SAVE_DETAILS = new StudentDetails(1002L, "Dave Blane", "Alice Blane", "dave@gmail.com",
                                                            "+10376544355", "Magic", TEST_STUDENT_4);
    public static final StudentDetails TEST_UPDATE_DETAILS = new StudentDetails(null, "UPDATE", "UPDATE",
                                                    "UPDATE@gmail.com", "+71111111111", "UPDATE", TEST_STUDENT_2);
}
