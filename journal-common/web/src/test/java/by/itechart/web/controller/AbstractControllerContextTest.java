package by.itechart.web.controller;

import by.itechart.web.AbstractContextTest;
import by.itechart.web.controller.util.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

@AutoConfigureMockMvc
public abstract class AbstractControllerContextTest extends AbstractContextTest {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected JsonParser jsonParser;

}
