package by.itechart.web.controller.user;

import by.itechart.mapping.dto.user.NoPassUserDto;
import by.itechart.mapping.dto.user.UserDto;
import by.itechart.mapping.user.UserMapper;
import by.itechart.model.user.User;
import by.itechart.web.controller.AbstractControllerContextTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;

import java.util.List;

import static by.itechart.web.data.UserTestData.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@Slf4j
@WithMockUser(username = "petr@gmail.com", authorities = "ADMIN" )
class AdminControllerTest extends AbstractControllerContextTest {

    @Autowired
    private UserMapper mapper;


    private static final String BASE_URL = "/users";


    @Test
    public void shouldStatusBeCreatedAndSaveUserProperly() throws Exception {

        log.info("Test saveUser() method with ADMIN role");

        UserDto request = mapper.userToUserDto(TEST_STORING_USER);


        mockMvc.perform(post(BASE_URL + "/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonParser.getJsonObject(request)))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(jsonParser.getJsonObject(request)))
                .andExpect(status().isCreated())
                .andReturn();
    }

    @Test
    public void shouldStatusBeOkAndGetUserByIdProperly() throws Exception {

        log.info("Test getUserById() method with ADMIN role");

        UserDto response = mapper.userToUserDto(TEST_USER_1);

        mockMvc.perform(get(BASE_URL + "/user/" + TEST_USER_1.getId()))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(jsonParser.getJsonObject(response)))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void shouldStatusBeOkAndGetUserByNameProperly() throws Exception {

        log.info("Test getUserByName() method with ADMIN role");

        UserDto response = mapper.userToUserDto(TEST_USER_1);

        mockMvc.perform(get(BASE_URL + "/user")
                .contentType(MediaType.APPLICATION_JSON)
                .param("email", TEST_USER_1.getUsername()))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(jsonParser.getJsonObject(response)))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void shouldBeStatusOkAndUpdateUserProperly() throws Exception {

        log.info("Test updateUser() method");

        UserDto userDto = mapper.userToUserDto(TEST_UPDATE_USER);

        mockMvc.perform(put(BASE_URL + "/user/" + TEST_USER_1.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonParser.getJsonObject(userDto)))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(jsonParser.getJsonObject(userDto)))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void shouldBeStatusNoContentAndDeleteUserByIdProperly() throws Exception {

        log.info("Test deleteUserById() method with ADMIN role");

        mockMvc.perform(delete(BASE_URL + "/user/" + TEST_USER_1.getId()))
                .andDo(print())
                .andExpect(status().isNoContent())
                .andReturn();
    }

    @Test
    public void shouldBeStatusNoContentAndDeleteUserByUsernameProperly() throws Exception {

        log.info("Test deleteUserByUsername() with ADMIN role");

        mockMvc.perform(delete(BASE_URL + "/user")
                .param("email", TEST_USER_1.getUsername()))
                .andDo(print())
                .andExpect(status().isNoContent())
                .andReturn();
    }


    @Test
    public void shouldBeStatusOkAndGetAllUsersProperly() throws Exception {

        log.info("Test getAllUsers() with ADMIN role");

        List<User> users = List.of(TEST_USER_1, TEST_USER_2, TEST_USER_3);
        List<NoPassUserDto> response = mapper.userListToNoPassUserList(users);

        mockMvc.perform(get(BASE_URL))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(jsonParser.getJsonObject(response)))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void shouldBeStatusOkAndReturnAllUsersByAuthorityNameProperly() throws Exception {

        log.info("Test getAllUsersByAuthorityName() with ADMIN role");

        List<User> adminUsers = List.of(TEST_USER_2);
        List<NoPassUserDto> response = mapper.userListToNoPassUserList(adminUsers);

        mockMvc.perform(get(BASE_URL).param("role", "ADMIN"))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(jsonParser.getJsonObject(response)))
                .andExpect(status().isOk())
                .andReturn();
    }



}