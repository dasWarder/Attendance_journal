package by.itechart.web.controller.user;

import by.itechart.mapping.dto.user.NoPassUserDto;
import by.itechart.mapping.user.UserMapper;
import by.itechart.model.user.User;
import by.itechart.web.controller.util.JsonParser;
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

import java.util.List;

import static by.itechart.web.data.UserTestData.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@Sql(scripts = { "/db/student/populate.sql" })
@WithMockUser(username = "admin@gmail.com", authorities = "SUPER_ADMIN" )
class SuperAdminControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JsonParser parser;

    @Autowired
    private UserMapper mapper;


    private static final String BASE_URL = "/admin/users";

    @Test
    public void shouldBeStatusCreatedAndSaveUserProperly() throws Exception {

        log.info("Test saveUser() with SUPER_ADMIN role");

        NoPassUserDto dto = mapper.userToNoPassUserDto(TEST_STORE_WITH_DEFAULT_PASS_USER);

        mockMvc.perform(post(BASE_URL + "/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(parser.getJsonObject(dto)))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(parser.getJsonObject(dto)))
                .andExpect(status().isCreated())
                .andReturn();
    }


    @Test
    public void shouldBeStatusOkAndGetUserByIdProperly() throws Exception {

        log.info("Test getUserById() with SUPER_ADMIN role");

        NoPassUserDto response = mapper.userToNoPassUserDto(TEST_USER_1);

        mockMvc.perform(get(BASE_URL + "/user/" + TEST_USER_1.getId()))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(parser.getJsonObject(response)))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void shouldBeStatusOkAndUpdateUserProperly() throws Exception {

        log.info("Test updateUser() with SUPER_ADMIN role");

        NoPassUserDto dto = mapper.userToNoPassUserDto(TEST_UPDATE_USER);

        mockMvc.perform(put(BASE_URL + "/user/" + TEST_USER_1.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(parser.getJsonObject(dto)))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(parser.getJsonObject(dto)))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void shouldBeStatusNoContentAndDeleteUserByIdProperly() throws Exception {

        log.info("Test deleteUserById() with SUPER_ADMIN role");

        mockMvc.perform(delete(BASE_URL + "/user/" + TEST_USER_1.getId()))
                .andDo(print())
                .andExpect(status().isNoContent())
                .andReturn();
    }

    @Test
    public void shouldBeStatusNoContentAndDeleteUserUsernameProperly() throws Exception {

        log.info("Test deleteUserByUsername() with SUPER_ADMIN role");

        mockMvc.perform(delete(BASE_URL + "/user")
                .contentType(MediaType.APPLICATION_JSON)
                .param("email", TEST_USER_1.getUsername()))
                .andDo(print())
                .andExpect(status().isNoContent())
                .andReturn();
    }

    @Test
    public void shouldBeStatusOkAndGetAllUsersProperly() throws Exception {

        log.info("Test getAllUsers() with SUPER_ADMIN role");

        List<User> allUsers = List.of(TEST_USER_1, TEST_USER_2, TEST_USER_3);
        List<NoPassUserDto> noPassDtoUserList = mapper.userListToNoPassUserList(allUsers);

        mockMvc.perform(get(BASE_URL))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(parser.getJsonObject(noPassDtoUserList)))
                .andExpect(status().isOk())
                .andReturn();

    }

    @Test
    public void shouldBeStatusOkAndGetAllUsersByAuthorityNameProperly() throws Exception {

        log.info("Test getAllUsersByAuthority() with SUPER_ADMIN role");

        List<User> usersWithUserRole = List.of(TEST_USER_1);
        List<NoPassUserDto> noPassUserDtoListOfUsersByUserRole = mapper.userListToNoPassUserList(usersWithUserRole);

        mockMvc.perform(get(BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .param("role", "USER"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(parser.getJsonObject(noPassUserDtoListOfUsersByUserRole)))
                .andExpect(status().isOk())
                .andReturn();
    }
}