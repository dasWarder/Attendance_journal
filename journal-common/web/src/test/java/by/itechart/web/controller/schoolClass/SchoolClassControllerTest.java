package by.itechart.web.controller.schoolClass;

import by.itechart.mapping.dto.schoolClass.SchoolClassDto;
import by.itechart.mapping.schoolClass.SchoolClassMapper;
import by.itechart.model.SchoolClass;
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

import static by.itechart.web.data.SchoolClassTestData.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@Sql(scripts = { "/db/student/populate.sql" })
@WithMockUser(username = "alex@gmail.com", authorities = "USER")
class SchoolClassControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JsonParser jsonParser;

    @Autowired
    private SchoolClassMapper schoolClassMapper;

    private static final String BASE_URL = "/classes";


    @Test
    public void shouldBeStatusOkAndReturnAllSchoolClassesProperly() throws Exception {

        log.info("Receive a list of school classes dto from method getAllSchoolClasses() and status OK");

        List<SchoolClass> schoolClassesOrigin = List.of(TEST_SCHOOL_CLASS_1);
        List<SchoolClassDto> response = schoolClassMapper.schoolClassListToSchoolClassDtoList(schoolClassesOrigin);

        mockMvc.perform(get(BASE_URL))
                                    .andDo(print())
                                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                                    .andExpect(content().json(jsonParser.getJsonObject(response)))
                                    .andExpect(status().isOk())
                                    .andReturn();
    }

    @Test
    public void shouldBeStatusOkAndReturnGetSchoolClassByIdProperly() throws Exception {

        log.info("Receive a school class dto from method getSchoolClassById() and status OK");
        SchoolClassDto response = schoolClassMapper.schoolClassToSchoolClassDto(TEST_SCHOOL_CLASS_1);

        mockMvc.perform(get(BASE_URL + "/class/"
                + TEST_SCHOOL_CLASS_1.getId()))
                                            .andDo(print())
                                            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                                            .andExpect(content().json(jsonParser.getJsonObject(response)))
                                            .andExpect(status().isOk())
                                            .andReturn();
    }

    @Test
    public void shouldBeStatusOkAndReturnGetSchoolClassByNameProperly() throws Exception {

        log.info("Receive a school class dto from method getSchoolClassByName() and status OK");
        SchoolClassDto response = schoolClassMapper.schoolClassToSchoolClassDto(TEST_SCHOOL_CLASS_1);

        mockMvc.perform(get(BASE_URL + "/class")
                .contentType(MediaType.APPLICATION_JSON)
                .param("name", TEST_SCHOOL_CLASS_1.getName()))
                    .andDo(print())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(content().json(jsonParser.getJsonObject(response)))
                    .andExpect(status().isOk())
                    .andReturn();
    }


    @Test
    public void shouldBeStatusCreateAndReturnSaveSchoolClassProperly() throws Exception {

        log.info("Store a school class and return a school class dto from method saveSchoolClass() and status CREATE");

        SchoolClassDto request = schoolClassMapper.schoolClassToSchoolClassDto(TEST_SAVE_SCHOOL_CLASS);
        SchoolClassDto response = request;

        mockMvc.perform(post(BASE_URL + "/class")
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content(jsonParser.getJsonObject(request)))
                                    .andDo(print())
                                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                                    .andExpect(content().json(jsonParser.getJsonObject(response)))
                                    .andExpect(status().isCreated())
                                    .andReturn();
    }

    @Test
    public void shouldBeStatusOkAndReturnUpdateSchoolClassProperly() throws Exception {

        log.info("Update a school class and return school class dto for the method updateSchoolClass() and status OK");

        SchoolClassDto request = schoolClassMapper.schoolClassToSchoolClassDto(TEST_UPDATE_SCHOOL_CLASS);
        SchoolClassDto response = request;

        mockMvc.perform(put(BASE_URL + "/class/" + TEST_SCHOOL_CLASS_1.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonParser.getJsonObject(request)))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(jsonParser.getJsonObject(response)))
                .andExpect(status().isOk())
                .andReturn();

    }


    @Test
    public void shouldStatusBeOkAndReturnDeleteSchoolClassByIdProperly() throws Exception {

        log.info("Remove a school class by its id from the method deleteSchoolClassById() and status OK");

        mockMvc.perform(delete(BASE_URL + "/class/"
                + TEST_SCHOOL_CLASS_1.getId()))
                                            .andDo(print())
                                            .andExpect(status().isNoContent())
                                            .andReturn();
    }

    @Test
    public void shouldStatusBeOkAndReturnDeleteSchoolClassByNameProperly() throws Exception {

        log.info("Remove a school class by its name from the method deleteSchoolClassByName() and status OK");

        mockMvc.perform(delete(BASE_URL + "/class")
                                    .param("name", TEST_SCHOOL_CLASS_1.getName()))
                                        .andDo(print())
                                        .andExpect(status().isNoContent())
                                        .andReturn();
    }

}