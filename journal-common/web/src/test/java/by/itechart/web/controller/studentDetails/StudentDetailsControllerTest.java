package by.itechart.web.controller.studentDetails;

import by.itechart.mapping.dto.studentDetails.StudentDetailsDto;
import by.itechart.mapping.studentDetails.StudentDetailsMapper;
import by.itechart.web.controller.AbstractControllerContextTest;
import by.itechart.web.data.SchoolClassTestData;
import by.itechart.web.data.StudentTestData;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;

import static by.itechart.web.data.SchoolClassTestData.TEST_SCHOOL_CLASS_1;

import static by.itechart.web.data.StudentDetailsTestData.*;
import static by.itechart.web.data.StudentTestData.TEST_STUDENT_2;
import static by.itechart.web.data.StudentTestData.TEST_STUDENT_4;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@Slf4j
@WithMockUser(username = "alex@gmail.com", authorities = "USER")
class StudentDetailsControllerTest extends AbstractControllerContextTest {

    @Autowired
    private StudentDetailsMapper mapper;

    private static final String BASE_URI = "/classes/class/%d/students/student/%d/details";


    @Test
    public void shouldReturnStatusCreatedAndReturnStoredStudentDetailsProperly() throws Exception {

        log.info("Store student details and return StudentDetails DTO and status isCREATED");

        StudentDetailsDto requestDto = mapper.studentDetailsToStudentDetailsDto(TEST_SAVE_DETAILS);

        mockMvc.perform(post(
                        String.format(BASE_URI, TEST_SCHOOL_CLASS_1.getId(), TEST_STUDENT_4.getId()))
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonParser.getJsonObject(requestDto)))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(jsonParser.getJsonObject(requestDto)))
                .andExpect(status().isCreated())
                .andReturn();
    }

    @Test
    public void shouldReturnStatusOkAndGetStudentDetailsProperly() throws Exception {

        log.info("Test getStudentByClassIdAndStudentId() method and return StudentDetails DTO and status is OK");

        Long classId = TEST_SCHOOL_CLASS_1.getId();
        Long studentId = TEST_STUDENT_2.getId();

        StudentDetailsDto response = mapper.studentDetailsToStudentDetailsDto(TEST_DETAILS_1);

        mockMvc.perform(get(String.format(BASE_URI, classId, studentId)))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(jsonParser.getJsonObject(response)))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void shouldReturnStatusOkAndDeleteStudentDetailsProperly() throws Exception {

        log.info("Test deleteStudentDetails() method and return StudentDetails DTO and status is OK");

        Long classId = TEST_SCHOOL_CLASS_1.getId();
        Long studentId = TEST_STUDENT_2.getId();

        mockMvc.perform(delete(String.format(BASE_URI, classId, studentId)))
                .andDo(print())
                .andExpect(status().isNoContent())
                .andReturn();
    }


    @Test
    public void shouldReturnStatusOkAndUpdateStudentDetailsProperly() throws Exception {

        log.info("Test updateStudentDetails() method and return StudentDetails DTO and status is OK");

        Long classId = TEST_SCHOOL_CLASS_1.getId();
        Long studentId = TEST_STUDENT_2.getId();

        StudentDetailsDto requestDto = mapper.studentDetailsToStudentDetailsDto(TEST_UPDATE_DETAILS);

        mockMvc.perform(put(String.format(BASE_URI, classId, studentId))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonParser.getJsonObject(requestDto)))
                        .andDo(print())
                        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                        .andExpect(content().json(jsonParser.getJsonObject(requestDto)))
                        .andExpect(status().isOk())
                        .andReturn();
    }
}