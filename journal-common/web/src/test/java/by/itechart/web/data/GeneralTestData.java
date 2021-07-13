package by.itechart.web.data;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public class GeneralTestData {

    public static final String LOGGED_USERNAME = "alex@gmail.com";

    public static final Pageable PAGE_PARAM = PageRequest.of(0, 25);
}
