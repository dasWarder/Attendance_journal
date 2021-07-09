package by.itechart.service.authority;

import by.itechart.model.exception.AuthorityNotFoundException;
import by.itechart.model.user.UserAuthority;
import by.itechart.repository.AuthorityRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

import static by.itechart.service.TestData.*;
import static by.itechart.service.authority.AuthorityTestData.TEST_UPDATED_AUTHORITY;
import static by.itechart.service.authority.AuthorityTestData.TEST_UPDATE_AUTHORITY;
import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class AuthorityServiceTest {

    private final AuthorityRepository authorityRepository = Mockito.mock(AuthorityRepository.class);

    private final AuthorityService authorityService = new AuthorityServiceImpl(authorityRepository);

    @Test
    public void shouldSaveAuthorityMethodWorksProperly() {

        log.info("Test saveAuthority() method");
        Mockito.when(authorityRepository.save(TEST_AUTHORITY_1))
                                                               .thenReturn(TEST_AUTHORITY_1);

        UserAuthority userAuthority = authorityService.saveAuthority(TEST_AUTHORITY_1);

        assertThat(userAuthority)
                                .usingRecursiveComparison()
                                .isEqualTo(TEST_AUTHORITY_1);
    }

    @Test
    public void shouldThrowExceptionWhenSaveAuthorityWithNullableParam() {

        log.info("Test saveAuthority() method throw exception when the param is equals NULL");

        Assertions.assertThrows(NullPointerException.class,
                                                            () -> authorityService.saveAuthority(null));
    }

    @Test
    public void shouldGetAuthorityByIdMethodWorksProperly() throws Throwable {

        log.info("Test getAuthorityById() method");

        Long authorityId = TEST_AUTHORITY_1.getId();
        Mockito.when(authorityRepository.findById(authorityId))
                                                            .thenReturn(Optional.of(TEST_AUTHORITY_1));

        UserAuthority authorityById = authorityService.getAuthorityById(authorityId);

        assertThat(authorityById)
                                .usingRecursiveComparison()
                                .isEqualTo(TEST_AUTHORITY_1);
    }

    @Test
    public void shouldThrowExceptionWhenGetAuthorityByIdWithNullableParam() {

        log.info("Test getAuthorityById() method throw an exception when the param is equals NULL");

        Assertions.assertThrows(NullPointerException.class,
                                                         () -> authorityService.getAuthorityById(null));
    }

    @Test
    public void shouldThrowExceptionWhenGetAuthorityByIdWithWrongParam() {

        log.info("Test getAuthorityById() throw an exception when the param is wrong");
        Mockito.when(authorityRepository.findById(WRONG_ID))
                                                            .thenReturn(Optional.ofNullable(null));

        Assertions.assertThrows(AuthorityNotFoundException.class,
                                                                () -> authorityService.getAuthorityById(WRONG_ID));
    }

    @Test
    public void shouldGetAuthorityByNameMethodWorksProperly() throws Throwable {

        log.info("Test getAuthorityByName() method");

        String name = TEST_AUTHORITY_1.getAuthority();
        Mockito.when(authorityRepository.getUserAuthorityByAuthority(name))
                                                                            .thenReturn(Optional.of(TEST_AUTHORITY_1));

        UserAuthority authorityByName = authorityService.getAuthorityByName(name);

        assertThat(authorityByName)
                                    .usingRecursiveComparison()
                                    .isEqualTo(TEST_AUTHORITY_1);
    }

    @Test
    public void shouldThrowExceptionWhenGetAuthorityByNameWithNullableParam() {

        log.info("Test getAuthorityByName() method throw an exception when the param is NULL");

        Assertions.assertThrows(NullPointerException.class,
                                                            () -> authorityService.getAuthorityByName(null));
    }

    @Test
    public void shouldThrowExceptionWhenGetAuthorityByNameWithWrongParam() throws Throwable {

        log.info("Test getAuthorityByName() throw an exception when the param is wrong");
        Mockito.when(authorityRepository.getUserAuthorityByAuthority(WRONG_NAME))
                                                                                 .thenReturn(Optional.ofNullable(null));

        Assertions.assertThrows(AuthorityNotFoundException.class,
                                                                 () -> authorityService.getAuthorityByName(WRONG_NAME));
    }


    @Test
    public void shouldGetAllAuthoritiesMethodWorksProperly() {

        log.info("Test getAllAuthorities() method");

        List<UserAuthority> actualAuthorities = List.of(TEST_AUTHORITY_1, TEST_AUTHORITY_2);
        Mockito.when(authorityRepository.findAll())
                                                   .thenReturn(actualAuthorities);

        List<UserAuthority> allAuthorities = authorityService.getAllAuthorities();

        assertThat(allAuthorities)
                                .usingRecursiveComparison()
                                .isEqualTo(actualAuthorities);
    }

    @Test
    public void shouldDeleteAuthorityByIdMethodWorksProperly() {

        log.info("Test deleteAuthorityById() method");
        Long authorityId = TEST_AUTHORITY_1.getId();

        authorityService.deleteAuthorityById(authorityId);
    }

    @Test
    public void shouldThrowExceptionWhenDeleteAuthorityByIdWithNullableParam() {

        log.info("Test deleteAuthorityById() method throw an exception when the param is equals NULL");

        Assertions.assertThrows(NullPointerException.class,
                                                            () -> authorityService.deleteAuthorityById(null));
    }

    @Test
    public void shouldDeleteAuthorityByNameMethodWorksProperly() {

        log.info("Test deleteAuthorityByName() method");
        String name = TEST_AUTHORITY_1.getAuthority();

        authorityService.deleteAuthorityByName(name);
    }

    @Test
    public void shouldThrowExceptionWhenDeleteAuthorityByNameWithNullableParam() {

        log.info("Test deleteAuthorityByName() method throw an exception when the param is equals NULL");

        Assertions.assertThrows(NullPointerException.class,
                                                           () -> authorityService.deleteAuthorityByName(null));
    }


    @Test
    public void shouldUpdateAuthorityMethodWorksProperly() throws Throwable {

        log.info("Test updateAuthority() method");

        Long authorityId = TEST_AUTHORITY_1.getId();
        Mockito.when(authorityRepository.findById(authorityId))
                                                                .thenReturn(Optional.of(TEST_AUTHORITY_1));
        UserAuthority updateAuthority = TEST_UPDATE_AUTHORITY;
        Mockito.when(authorityRepository.save(updateAuthority))
                                                               .thenReturn(updateAuthority);

        UserAuthority userAuthority = authorityService.updateAuthority(authorityId, updateAuthority);

        assertThat(userAuthority)
                                .usingRecursiveComparison()
                                .isEqualTo(TEST_UPDATED_AUTHORITY);

    }

    @Test
    public void shouldThrowExceptionWhenUpdateAuthorityWithNullableFirstParam() {

        log.info("Test updateAuthority() method throw an exception when the first param is equals NULL");
        UserAuthority updateAuthority = TEST_UPDATE_AUTHORITY;

        Assertions.assertThrows(NullPointerException.class,
                                                    () -> authorityService.updateAuthority(null, updateAuthority));
    }

    @Test
    public void shouldThrowExceptionWhenUpdateAuthorityWithNullableSecondParam() {

        log.info("Test updateAuthority() method throw an exception when the second param is equals NULL");
        Long authorityId = TEST_AUTHORITY_1.getId();

        Assertions.assertThrows(NullPointerException.class,
                                                    () -> authorityService.updateAuthority(authorityId, null));
    }


    @Test
    public void shouldThrowExceptionWhenUpdateAuthorityWithWrongFirstParam() {

        log.info("Test updateAuthority() method throw an exception when the first param is wrong");

        UserAuthority updateAuthority = TEST_UPDATE_AUTHORITY;
        Mockito.when(authorityRepository.findById(WRONG_ID))
                                                            .thenReturn(Optional.ofNullable(null));

        Assertions.assertThrows(AuthorityNotFoundException.class,
                                                            () -> authorityService.updateAuthority(WRONG_ID, updateAuthority));
    }
}