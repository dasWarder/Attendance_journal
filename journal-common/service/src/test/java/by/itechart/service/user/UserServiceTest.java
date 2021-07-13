package by.itechart.service.user;

import by.itechart.model.exception.UserNotFoundException;
import by.itechart.model.user.User;
import by.itechart.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

import static by.itechart.service.TestData.*;
import static by.itechart.service.user.UserTestData.*;
import static org.assertj.core.api.Assertions.assertThat;


@Slf4j
class UserServiceTest {

    private final UserRepository userRepository = Mockito.mock(UserRepository.class);

    private final PasswordEncoder encoder = new BCryptPasswordEncoder();

    private final UserService userService = new UserServiceImpl(userRepository, encoder);


    @Test
    public void shouldSaveUserMethodWorksProperly() {

        log.info("Test saveUser() method");

        Mockito.when(userRepository.save(TEST_SAVE_USER))
                                                        .thenReturn(TEST_SAVE_USER);
        User storedUser = userService.saveUser(TEST_SAVE_USER);

        assertThat(storedUser)
                            .usingRecursiveComparison()
                            .isEqualTo(TEST_SAVE_USER);
    }

    @Test
    public void shouldThrowExceptionWhenSaveUserMethodWithNullableParam() {

        log.info("Test saveUser() throws an exception when the param value is equals to NULL");

        Assertions.assertThrows(NullPointerException.class,
                                                          () -> userService.saveUser(null));
    }

    @Test
    public void shouldGetUserByIdMethodWorksProperly() throws Throwable {

        log.info("Test getUserById() method");

        Long userId = TEST_USER_1.getId();
        Mockito.when(userRepository.findById(userId))
                                                    .thenReturn(Optional.of(TEST_USER_1));
        User userById = userService.getUserById(userId);

        assertThat(userById)
                            .usingRecursiveComparison()
                            .isEqualTo(TEST_USER_1);
    }

    @Test
    public void shouldThrowExceptionWhenGetUserByIdWithNullableParam() {

        log.info("Test getUserById() throws an exception when the param is equals to NULL");

        Assertions.assertThrows(NullPointerException.class,
                                                            () -> userService.getUserById(null));
    }

    @Test
    public void shouldThrowExceptionWhenGetUserByIdWithWrongParam() {

        log.info("Test getUserById() throws an exception when the param is a wrong ID");

        Mockito.when(userRepository.findById(WRONG_ID))
                                                       .thenReturn(Optional.ofNullable(null));

        Assertions.assertThrows(UserNotFoundException.class,
                                                            () -> userService.getUserById(WRONG_ID));
    }


    @Test
    public void shouldGetUserByUsernameMethodWorksProperly() throws Throwable {

        log.info("Test getUserByUsername() method");

        String username = TEST_USER_1.getUsername();
        Mockito.when(userRepository.getUserByUsername(username))
                                                                .thenReturn(Optional.of(TEST_USER_1));
        User userByUsername = userService.getUserByUsername(username);

        assertThat(userByUsername)
                                  .usingRecursiveComparison()
                                  .isEqualTo(TEST_USER_1);
    }

    @Test
    public void shouldThrowExceptionWhenGetUserByUsernameWithNullableParam() {

        log.info("Test getUserByUsername() throws an exception when the param is equals to NULL");

        Assertions.assertThrows(NullPointerException.class,
                                                        () -> userService.getUserByUsername(null));
    }

    @Test
    public void shouldThrowExceptionWhenGetUserByUsernameWithWrongParam() {

        log.info("Test getUserByUsername() throws an exception when the param is a wrong ID");

        Mockito.when(userRepository.getUserByUsername(WRONG_NAME))
                                                                    .thenReturn(Optional.ofNullable(null));

        Assertions.assertThrows(UserNotFoundException.class,
                                                            () -> userService.getUserByUsername(WRONG_NAME));
    }

    @Test
    public void shouldDeleteUserByIdMethodWorksProperly() {

        log.info("Test deleteUserById() method");
        Long userId = TEST_USER_1.getId();

        userService.deleteUserById(userId);
    }

    @Test
    public void shouldThrowExceptionWhenDeleteUserBydIdWithNullableParam() {

        log.info("Test deleteUserById() throws an exception when the param is equals to NULL");

        Assertions.assertThrows(NullPointerException.class,
                                                           () -> userService.deleteUserById(null));
    }

    @Test
    public void shouldDeleteUserByUsernameMethodWorksProperly() {

        log.info("Test deleteUserByName() method");
        String username = TEST_USER_1.getUsername();

        userService.deleteUserByName(username);
    }

    @Test
    public void shouldThrowExceptionWhenDeleteUserByUsernameWithNullableParam() {

        log.info("Test deleteUserByName() throws an exception when the param is equals to NULL");

        Assertions.assertThrows(NullPointerException.class,
                                                           () -> userService.deleteUserByName(null));
    }


    @Test
    public void shouldUpdateUserMethodWorksProperly() throws Throwable {

        log.info("Test updateUser() method");

        Long userId = TEST_USER_1.getId();
        Mockito.when(userRepository.findById(userId)).thenReturn(Optional.of(TEST_USER_1));

        User user = TEST_UPDATE_USER;
        Mockito.when(userRepository.save(user))
                                              .thenReturn(user);

        User updatedUser = userService.updateUser(userId, user);

        assertThat(updatedUser)
                                .usingRecursiveComparison()
                                .isEqualTo(TEST_UPDATED_USER);
    }

    @Test
    public void shouldThrowExceptionWhenUpdateUserWithNullableFirstParam() {

        log.info("Test updateUser() throws an exception when the first param is equals to NULL");

        Assertions.assertThrows(NullPointerException.class,
                                                            () -> userService.updateUser(null, TEST_UPDATE_USER));
    }

    @Test
    public void shouldThrowExceptionWhenUpdateUserWithNullableSecondParam() {

        log.info("Test updateUser() throws an exception when the second param is equals to NULL");

        Long userId = TEST_USER_1.getId();

        Assertions.assertThrows(NullPointerException.class,
                                                          () -> userService.updateUser(userId, null));
    }

    @Test
    public void shouldThrowExceptionWhenUpdateUserWithWrongFirstParam() {

        log.info("Test updateUser() throws an exception when the first param is wrong");

        Mockito.when(userRepository.findById(WRONG_ID))
                                                      .thenReturn(Optional.ofNullable(null));
        Assertions.assertThrows(UserNotFoundException.class,
                                                            () -> userService.updateUser(WRONG_ID, TEST_UPDATE_USER));
    }

    @Test
    public void shouldGetAllUsersMethodWorksProperly() {

        log.info("Test getAllUsers() method");

        List<User> expectedList = List.of(TEST_USER_1, TEST_USER_2);
        Mockito.when(userRepository.findAll()).thenReturn(expectedList);

        List<User> allUsers = userService.getAllUsers();

        assertThat(allUsers)
                            .usingRecursiveComparison()
                            .isEqualTo(expectedList);
    }

    @Test
    public void shouldGetUsersByRoleAuthorityMethodWorksProperly() {

        log.info("Test getUsersByRoleAuthority() method");

        String roleName = TEST_AUTHORITY_1.getAuthority();
        List<User> expectedUsers = List.of(TEST_USER_1);
        Mockito.when(userRepository.getUsersByRole_Authority(roleName))
                                                                       .thenReturn(expectedUsers);

        List<User> allUsers = userService.getAllUsersByAuthority(roleName);

        assertThat(allUsers)
                            .usingRecursiveComparison()
                            .isEqualTo(expectedUsers);
    }

    @Test
    public void shouldThrowExceptionWhenGetUsersByRoleAuthorityWithNullableParam() {

        log.info("Test getUsersByRoleAuthority() throws an exception when the first param is equals to NULL");

        Assertions.assertThrows(NullPointerException.class,
                                                           () -> userService.getAllUsersByAuthority(null));
    }

    @Test
    public void shouldWhenGetUsersByRoleAuthorityReturnNullWithWrongParam() {

        log.info("Test getUsersByRoleAuthority() throws an exception when the param is wrong");

        Mockito.when(userRepository.getUsersByRole_Authority(WRONG_NAME))
                                                                        .thenReturn(null);

        List<User> allUsersByAuthority = userService.getAllUsersByAuthority(WRONG_NAME);

        Assertions.assertNull(allUsersByAuthority);
    }

}