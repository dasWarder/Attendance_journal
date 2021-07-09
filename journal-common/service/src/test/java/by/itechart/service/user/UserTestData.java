package by.itechart.service.user;

import by.itechart.model.user.User;

import static by.itechart.service.TestData.TEST_AUTHORITY_1;
import static by.itechart.service.TestData.TEST_AUTHORITY_2;

public class UserTestData {

    public static final User TEST_USER_1 = new User(
                                           1L, "alex@gmail.com", "12345", true, TEST_AUTHORITY_1);

    public static final User TEST_USER_2 = new User(
                                        2L, "petr@gmail.com", "12345", true, TEST_AUTHORITY_2);

    public static final User TEST_SAVE_USER = new User(
                                             3L, "new@gmail.com", "12345", true, TEST_AUTHORITY_2);

    public static final User TEST_UPDATE_USER = new User(
                                 "update@gmail.com", "123" , true, TEST_AUTHORITY_2);

    public static final User TEST_UPDATED_USER = new User(
                                TEST_USER_1.getId(), "update@gmail.com", "123" , true, TEST_AUTHORITY_2);
}
