package by.itechart.web.integration;

import by.itechart.model.exception.AuthorityNotFoundException;
import by.itechart.model.user.UserAuthority;
import by.itechart.service.authority.AuthorityService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static by.itechart.web.data.AuthorityTestData.*;
import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@SpringBootTest
@ExtendWith(SpringExtension.class)
@Sql(scripts = { "/db/student/populate.sql" })
public class AuthorityServiceIntegrationTest {

    @Autowired
    private AuthorityService authorityService;

    @Test
    public void shouldReturnListOfAllAuthoritiesProperly() {

        log.info("Test getAllAuthorities() method");

        List<UserAuthority> testAuthorities = List.of(TEST_AUTHORITY_1, TEST_AUTHORITY_2, TEST_AUTHORITY_3);

        List<UserAuthority> authorities = authorityService.getAllAuthorities();

        assertThat(authorities)
                                .usingRecursiveComparison()
                                .ignoringFields("users")
                                .isEqualTo(testAuthorities);

    }

    @Test
    public void shouldReturnAuthorityByIdProperly() throws Throwable {

        log.info("Test getAuthorityById() method");

        UserAuthority authorityById = authorityService.getAuthorityById(TEST_AUTHORITY_1.getId());

        assertThat(authorityById)
                                .usingRecursiveComparison()
                                .ignoringFields("users")
                                .isEqualTo(TEST_AUTHORITY_1);
    }

    @Test
    public void shouldReturnAuthorityByNameProperly() throws Throwable {

        log.info("Test getAuthorityByName() method");

        UserAuthority authorityByName = authorityService.getAuthorityByName(TEST_AUTHORITY_1.getAuthority());

        assertThat(authorityByName)
                                    .usingRecursiveComparison()
                                    .ignoringFields("users")
                                    .isEqualTo(TEST_AUTHORITY_1);
    }

    @Test
    public void shouldDeleteAuthorityByIdProperly() {

        log.info("Test deleteAuthorityById() method");

        authorityService.deleteAuthorityById(TEST_AUTHORITY_1.getId());

        Assertions.assertThrows(AuthorityNotFoundException.class,
                                        () -> authorityService.getAuthorityById(TEST_AUTHORITY_1.getId()));
    }

    @Test
    public void shouldDeleteAuthorityByNameProperly() {

        log.info("Test deleteAuthorityByName() method");

        authorityService.deleteAuthorityByName(TEST_AUTHORITY_1.getAuthority());

        Assertions.assertThrows(AuthorityNotFoundException.class,
                                        () -> authorityService.getAuthorityByName(TEST_AUTHORITY_1.getAuthority()));
    }

    @Test
    public void shouldSaveAuthorityProperly() {

        log.info("Test saveAuthority() method");

        UserAuthority userAuthority = authorityService.saveAuthority(TEST_STORING_AUTHORITY);

        assertThat(userAuthority)
                                .usingRecursiveComparison()
                                .ignoringFields("users")
                                .isEqualTo(TEST_STORING_AUTHORITY);
    }


    @Test
    public void shouldUpdateAuthorityProperly() throws Throwable {

        log.info("Test updateAuthority() method");

        Long userId = TEST_AUTHORITY_1.getId();
        UserAuthority update = TEST_UPDATE_AUTHORITY;

        UserAuthority updatedAuthority = authorityService.updateAuthority(userId, update);
        update.setId(updatedAuthority.getId());

        assertThat(updatedAuthority)
                                    .usingRecursiveComparison()
                                    .ignoringFields("users")
                                    .isEqualTo(update);
    }
}
