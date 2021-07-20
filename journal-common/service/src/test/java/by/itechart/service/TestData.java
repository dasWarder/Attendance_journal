package by.itechart.service;

import by.itechart.model.SchoolClass;
import by.itechart.model.user.UserAuthority;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public class TestData {

    public static final Long WRONG_ID = 123456L;

    public static final String WRONG_NAME = "Wrong";

    public static final UserAuthority TEST_AUTHORITY_1 = new UserAuthority(1L, "USER");

    public static final UserAuthority TEST_AUTHORITY_2 = new UserAuthority(2L, "ADMIN");

    public static final SchoolClass TEST_SCHOOL_CLASS_1 = new SchoolClass(1L, "1А");

    public static final SchoolClass TEST_SCHOOL_CLASS_2 = new SchoolClass(2L, "1Б");

    public static final Pageable PAGE_PARAM = PageRequest.of(0, 25);


}
