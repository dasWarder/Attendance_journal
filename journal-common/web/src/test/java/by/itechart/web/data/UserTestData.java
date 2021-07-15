package by.itechart.web.data;

import by.itechart.mapping.dto.user.RegisterUserDto;
import by.itechart.model.user.User;

import static by.itechart.web.data.AuthorityTestData.*;

public class UserTestData {

    public static final User TEST_USER_1 = new User(100003L,  "alex@gmail.com",
                                             "$2a$10$o53h1x/Y2shcaj0XTM71ju3G06I9EVxoH1wcNwYpY4cf3vvscNsiu",
                                               true, AuthorityTestData.TEST_AUTHORITY_1);

    public static final User TEST_USER_2 = new User(100004L,  "petr@gmail.com",
                                            "$2a$10$o53h1x/Y2shcaj0XTM71ju3G06I9EVxoH1wcNwYpY4cf3vvscNsiu",
                                            true, TEST_AUTHORITY_2);

    public static final User TEST_USER_3 = new User(100005L,  "admin@gmail.com",
                                            "$2a$10$o53h1x/Y2shcaj0XTM71ju3G06I9EVxoH1wcNwYpY4cf3vvscNsiu",
                                            true, TEST_AUTHORITY_3);

    public static final User TEST_STORING_USER = new User(100006L, "stored@gmail.com",
                                                "$2a$10$o53h1x/Y2shcaj0XTM71ju3G06I9EVxoH1wcNwYpY4cf3vvscNsiu",
                                                true, TEST_AUTHORITY_1);

    public static final User TEST_UPDATE_USER = new User(TEST_USER_1.getId(), "update@gmail.com",
                                                "$2a$10$o53h1x/Y2shcaj0XTM71ju3G06I9EVxoH1wcNwYpY4cf3vvscNsiu",
                                                true, TEST_AUTHORITY_2);

    public static final RegisterUserDto TEST_REGISTER_USER = new RegisterUserDto();
}
