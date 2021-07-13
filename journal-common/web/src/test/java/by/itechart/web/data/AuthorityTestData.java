package by.itechart.web.data;

import by.itechart.model.user.UserAuthority;

public class AuthorityTestData {

    public static final UserAuthority TEST_AUTHORITY_1 = new UserAuthority(100000L,"USER");

    public static final UserAuthority TEST_AUTHORITY_2 = new UserAuthority(100001L, "ADMIN");

    public static final UserAuthority TEST_STORING_AUTHORITY = new UserAuthority(100004L, "TESTER");

    public static final UserAuthority TEST_UPDATE_AUTHORITY = new UserAuthority(null, "UPDATE");
}
