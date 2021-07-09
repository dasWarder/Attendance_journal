package by.itechart.service.authority;

import by.itechart.model.user.UserAuthority;

import static by.itechart.service.TestData.TEST_AUTHORITY_1;

public class AuthorityTestData {

    public static final UserAuthority TEST_UPDATE_AUTHORITY = new UserAuthority("UPDATE");

    public static final UserAuthority TEST_UPDATED_AUTHORITY = new UserAuthority(
                                                               TEST_AUTHORITY_1.getId(), TEST_UPDATE_AUTHORITY.getAuthority());
}
