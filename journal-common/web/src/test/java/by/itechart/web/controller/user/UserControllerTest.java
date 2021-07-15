package by.itechart.web.controller.user;

import by.itechart.mapping.dto.user.FullUserDto;
import by.itechart.mapping.dto.user.RegisterUserDto;
import by.itechart.mapping.user.UserMapper;
import by.itechart.mapping.user.UserMapperWithUserRole;
import by.itechart.web.controller.util.JsonParser;
import by.itechart.web.data.UserTestData;
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

import static by.itechart.web.data.UserTestData.TEST_UPDATE_USER;
import static by.itechart.web.data.UserTestData.TEST_USER_1;


import static by.itechart.web.data.AuthorityTestData.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@Sql(scripts = { "/db/student/populate.sql" })
@WithMockUser(username = "alex@gmail.com", authorities = "USER" )
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JsonParser parser;

    @Autowired
    private UserMapper mapper;

    private static final String BASE_URL = "/details";


    @Test
    public void shouldBeStatusOkAndReturnDetailsOfUserProperly() throws Exception {

        log.info("Test /details with USER role");

        FullUserDto userDto = mapper.userToFullUserDto(TEST_USER_1);

        mockMvc.perform(get(BASE_URL))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(parser.getJsonObject(userDto)))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void shouldBeStatusOkAndUpdateDetailsProperly() throws Exception {

        log.info("Test /details update with USER role");

        RegisterUserDto requestDto = mapper.userToRegisterUserDto(TEST_UPDATE_USER);
        FullUserDto responseDto = mapper.userToFullUserDto(TEST_UPDATE_USER);

        mockMvc.perform(put(BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(parser.getJsonObject(requestDto)))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(parser.getJsonObject(responseDto)))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void shouldBeStatusOkAndDeleteUserDetailsProperly() throws Exception {

        log.info("Test /details DELETE with USER role");

        mockMvc.perform(delete(BASE_URL))
                .andDo(print())
                .andExpect(status().isNoContent())
                .andReturn();
    }

}