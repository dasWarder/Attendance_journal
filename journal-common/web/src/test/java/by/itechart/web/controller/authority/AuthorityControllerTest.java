package by.itechart.web.controller.authority;

import by.itechart.mapping.authority.AuthorityMapper;
import by.itechart.mapping.dto.authority.AuthorityDto;
import by.itechart.model.user.UserAuthority;
import by.itechart.web.controller.AbstractControllerContextTest;
import by.itechart.web.controller.util.JsonParser;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static by.itechart.web.data.AuthorityTestData.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@Slf4j
@WithMockUser(username = "admin@gmail.com", authorities = "SUPER_ADMIN" )
class AuthorityControllerTest extends AbstractControllerContextTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JsonParser parser;

    @Autowired
    private AuthorityMapper mapper;

    private static final String BASE_URL = "/admin/roles";

    @Test
    public void shouldBeStatusOkAndReturnAllAuthoritiesProperly() throws Exception {

        log.info("Test getAllAuthorities() method");
        List<UserAuthority> authorities = List.of(TEST_AUTHORITY_1, TEST_AUTHORITY_2, TEST_AUTHORITY_3);
        List<AuthorityDto> dtoList = mapper.userAuthorityListToAuthorityDtoList(authorities);

        mockMvc.perform(get(BASE_URL)).andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(parser.getJsonObject(dtoList)))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void shouldBeStatusOkAndGetAuthorityByIdProperly() throws Exception {

        log.info("Test getAuthorityById() method");

        AuthorityDto authorityDto = mapper.userAuthorityToAuthorityDto(TEST_AUTHORITY_1);

        mockMvc.perform(get(BASE_URL + "/role/" + TEST_AUTHORITY_1.getId()))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(parser.getJsonObject(authorityDto)))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void shouldBeStatusOkAndGetAuthorityByNameProperly() throws Exception {

        log.info("Test getAuthorityByName() method");

        AuthorityDto authorityDto = mapper.userAuthorityToAuthorityDto(TEST_AUTHORITY_1);

        mockMvc.perform(get(BASE_URL + "/role")
                .param("name", TEST_AUTHORITY_1.getAuthority()))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(parser.getJsonObject(authorityDto)))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void shouldBeStatusOkAndDeleteAuthorityByIdProperly() throws Exception {

        log.info("Test deleteAuthorityById() method");

        mockMvc.perform(delete(BASE_URL + "/role/" + TEST_AUTHORITY_1.getId()))
                .andDo(print())
                .andExpect(status().isNoContent())
                .andReturn();
    }

    @Test
    public void shouldBeStatusOkAndDeleteAuthorityByNameProperly() throws Exception {

        log.info("Test deleteAuthorityByName() method");

        mockMvc.perform(delete(BASE_URL + "/role")
                .param("name", TEST_AUTHORITY_1.getAuthority()))
                .andDo(print())
                .andExpect(status().isNoContent())
                .andReturn();
    }

    @Test
    public void shouldBeStatusCreatedAndSaveAuthorityProperly() throws Exception {

        log.info("Test saveAuthority() method");

        AuthorityDto authorityDto = mapper.userAuthorityToAuthorityDto(TEST_STORING_AUTHORITY);

        mockMvc.perform(post(BASE_URL + "/role")
                .content(parser.getJsonObject(authorityDto))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(parser.getJsonObject(authorityDto)))
                .andExpect(status().isCreated())
                .andReturn();
    }

    @Test
    public void shouldBeStatusOkAndUpdateAuthorityProperly() throws Exception {

        log.info("Test updateAuthority() method");

        AuthorityDto authorityDto = mapper.userAuthorityToAuthorityDto(TEST_UPDATE_AUTHORITY);

        mockMvc.perform(put(BASE_URL + "/role/" + TEST_AUTHORITY_1.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(parser.getJsonObject(authorityDto)))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(parser.getJsonObject(authorityDto)))
                .andExpect(status().isOk())
                .andReturn();
    }


}