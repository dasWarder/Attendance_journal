package by.itechart.web.data;

import by.itechart.mapping.dto.user.RegisterUserDto;
import by.itechart.model.user.UserAuthority;

public class AuthorityTestData {

    public static final UserAuthority TEST_AUTHORITY_1 = new UserAuthority(100000L,"USER");

    public static final UserAuthority TEST_AUTHORITY_2 = new UserAuthority(100001L, "ADMIN");

    public static final UserAuthority TEST_AUTHORITY_3 = new UserAuthority(100002L, "SUPER_ADMIN");

    public static final UserAuthority TEST_STORING_AUTHORITY = new UserAuthority(100006L, "TESTER");

    public static final UserAuthority TEST_UPDATE_AUTHORITY = new UserAuthority(TEST_AUTHORITY_1.getId(), "UPDATE");


}
