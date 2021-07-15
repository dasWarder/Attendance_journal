package by.itechart.web.controller.user;

import by.itechart.mapping.dto.user.RegisterUserDto;
import by.itechart.mapping.dto.user.UserDto;
import by.itechart.mapping.user.UserMapper;
import by.itechart.mapping.user.UserMapperWithUserRole;
import by.itechart.model.user.User;
import by.itechart.web.controller.util.JsonParser;
import by.itechart.web.data.TokenTestData;
import by.itechart.web.data.UserTestData;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static by.itechart.web.data.TokenTestData.TEST_TOKEN_REQUEST;
import static by.itechart.web.data.UserTestData.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@Sql(scripts = { "/db/student/populate.sql" })
class RegisterControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JsonParser parser;

    @Autowired
    private UserMapper mapper;

    @Autowired
    private UserMapperWithUserRole customMapper;


    @Test
    public void shouldBeStatusCreatedAndCreateUserProperly() throws Throwable {

        log.info("Test /rigister from RegisterController");

        RegisterUserDto testRegisterUser = TEST_REGISTER_USER;
        testRegisterUser.setEmail(TEST_STORING_USER.getUsername());
        testRegisterUser.setPassword("1234567");

        User user = customMapper.registerUserDtoToUserWithRole(testRegisterUser);
        UserDto response = mapper.userToUserDto(user);

        mockMvc.perform(post("/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(parser.getJsonObject(testRegisterUser)))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(parser.getJsonObject(response)))
                .andExpect(status().isCreated())
                .andReturn();
    }


    @Test
    @WithMockUser(username = "alex@gmail.com", authorities = "USER")
    public void shouldBeStatusCreatedAndReturnTokenProperly() throws Exception {

        log.info("Test /auth for USER role");

        RegisterUserDto registerUserDto = TEST_REGISTER_USER;

        registerUserDto.setEmail(TEST_USER_1.getUsername());
        registerUserDto.setPassword("12345");

        mockMvc.perform(post("/auth")
                .contentType(MediaType.APPLICATION_JSON)
                .content(parser.getJsonObject(registerUserDto)))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();
    }

    @Test
    @WithMockUser(username = "alex@gmail.com", authorities = "USER")
    public void shouldBeStatusForbiddenAndThrowExceptionThatTokenExpired() throws Exception {

        log.info("Test /refresh for USER role");

        mockMvc.perform(post("/refresh")
                .contentType(MediaType.APPLICATION_JSON)
                .content(parser.getJsonObject(TEST_TOKEN_REQUEST)))
                .andDo(print())
                .andExpect(status().isForbidden())
                .andReturn();
    }
}