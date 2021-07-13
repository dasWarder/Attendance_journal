package by.itechart.web.data;

import by.itechart.model.user.User;

import static by.itechart.web.data.AuthorityTestData.TEST_AUTHORITY_1;
import static by.itechart.web.data.AuthorityTestData.TEST_AUTHORITY_2;

public class UserTestData {

    public static final User TEST_USER_1 = new User(100002L,  "alex@gmail.com",
                                             "$2a$10$o53h1x/Y2shcaj0XTM71ju3G06I9EVxoH1wcNwYpY4cf3vvscNsiu",
                                               true, AuthorityTestData.TEST_AUTHORITY_1);

    public static final User TEST_USER_2 = new User(100003L,  "petr@gmail.com",
                                            "$2a$10$o53h1x/Y2shcaj0XTM71ju3G06I9EVxoH1wcNwYpY4cf3vvscNsiu",
                                            true, TEST_AUTHORITY_2);

    public static final User TEST_STORING_USER = new User(100004L, "stored@gmail.com",
                                                "$2a$10$o53h1x/Y2shcaj0XTM71ju3G06I9EVxoH1wcNwYpY4cf3vvscNsiu",
                                                true, TEST_AUTHORITY_1);

    public static final User TEST_UPDATE_USER = new User(null, "update@gmail.com",
                                                "$2a$10$o53h1x/Y2shcaj0XTM71ju3G06I9EVxoH1wcNwYpY4cf3vvscNsiu",
                                                false, TEST_AUTHORITY_2);
}
