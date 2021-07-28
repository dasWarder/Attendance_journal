package by.itechart.web.integration;


import by.itechart.model.exception.UserNotFoundException;
import by.itechart.model.user.User;
import by.itechart.service.user.UserService;
import by.itechart.web.AbstractContextTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static by.itechart.web.data.UserTestData.*;
import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class UserServiceIntegrationContextTest extends AbstractContextTest {

    @Autowired
    private UserService userService;


    @Test
    public void shouldReturnAllUsersProperly() {

        log.info("Test getAllUsers() method");

        List<User> testUsers = List.of(TEST_USER_1, TEST_USER_2, TEST_USER_3);
        List<User> users = userService.getAllUsers();

        assertThat(users)
                        .usingRecursiveComparison()
                        .ignoringFields("role", "classes")
                        .isEqualTo(testUsers);
    }


    @Test
    public void shouldReturnUserByIdProperly() throws Throwable {

        log.info("Test getUserById() method");

        Long userId = TEST_USER_1.getId();

        User userById = userService.getUserById(userId);

        assertThat(userById)
                            .usingRecursiveComparison()
                            .ignoringFields("role", "classes")
                            .isEqualTo(TEST_USER_1);
    }

    @Test
    public void shouldReturnUserByNameProperly() throws Throwable {

        log.info("Test getUserByName() method");

        String username = TEST_USER_1.getUsername();

        User userByName = userService.getUserByUsername(username);

        assertThat(userByName)
                .usingRecursiveComparison()
                .ignoringFields("role", "classes")
                .isEqualTo(TEST_USER_1);
    }

    @Test
    public void shouldDeleteUserByIdProperly() {

        log.info("Test deleteUserById() method");

        Long userId = TEST_USER_1.getId();

        userService.deleteUserById(userId);

        Assertions.assertThrows(UserNotFoundException.class,
                                                            () -> userService.getUserById(userId));
    }

    @Test
    public void shouldDeleteUserByNameProperly() {

        log.info("Test deleteUserByName() method");

        String username = TEST_USER_1.getUsername();

        userService.deleteUserByName(username);

        Assertions.assertThrows(UserNotFoundException.class,
                                                            () -> userService.getUserByUsername(username));
    }

    @Test
    public void shouldSaveUserProperly() throws Throwable {

        log.info("Test saveUser() method");

        User store = TEST_STORING_USER;
        User storedUser = userService.saveUser(store);

        assertThat(storedUser)
                            .usingRecursiveComparison()
                            .ignoringFields("role", "classes")
                            .isEqualTo(store);
    }


    @Test
    public void shouldUpdateUserProperly() throws Throwable {

        log.info("Test updateUser() method");

        Long userId = TEST_USER_1.getId();
        User update = TEST_UPDATE_USER;

        User updatedUser = userService.updateUser(userId, update);
        update.setId(updatedUser.getId());

        assertThat(updatedUser)
                                .usingRecursiveComparison()
                                .ignoringFields("role", "classes")
                                .isEqualTo(update);
    }
    
    @Test
    public void shouldReturnListOfAllUsersByAuthorityNameProperly() {

        log.info("Test getAllUserByAuthority() method");

        List<User> testUsers = List.of(TEST_USER_1);
        String authorityName = "USER";


        List<User> users = userService.getAllUsersByAuthority(authorityName);

        assertThat(users)
                        .usingRecursiveComparison()
                        .ignoringFields("role", "classes")
                        .isEqualTo(testUsers);
    }
}
