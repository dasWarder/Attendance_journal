package by.itechart.web.controller.student;

import by.itechart.mapping.dto.student.StudentDto;
import by.itechart.mapping.dto.student.StudentDtoId;
import by.itechart.mapping.student.StudentMapper;
import by.itechart.model.Student;
import by.itechart.web.controller.AbstractControllerContextTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;

import java.util.List;

import static by.itechart.web.data.StudentTestData.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@WithMockUser(username = "alex@gmail.com", authorities = "USER")
class StudentControllerTest extends AbstractControllerContextTest {

    @Autowired
    private StudentMapper studentMapper;

    private static final String BASE_URL = "/classes/class/12";

    @Test
    public void shouldBeStatusOkAndReturnAllStudentsProperly() throws Exception {

        log.info("Receive a list of Student dto with id from method findAllStudents() and status OK");

        List<Student> studentsOrigin = List.of(TEST_STUDENT_2, TEST_STUDENT_3, TEST_STUDENT_4);
        List<StudentDtoId> students = studentMapper.studentListToStudentDtoIdList(studentsOrigin);

        mockMvc.perform(get(BASE_URL + "/students"))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(jsonParser.getJsonObject(students)))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void shouldBeStatusOkAndReturnGetStudentByIdProperly() throws Exception {

        log.info("Receive a student dto without id from method getStudentById() and status OK");
        StudentDto response = studentMapper.studentToStudentDto(TEST_STUDENT_2);

        mockMvc.perform(get(BASE_URL + "/students/student/" + TEST_STUDENT_2.getId()))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(jsonParser.getJsonObject(response)))
                .andExpect(status().isOk())
                .andReturn();
    }


    @Test
    public void shouldBeStatusCreateAndReturnSaveStudentProperly() throws Exception {

        log.info("Store a student and return student dto without id from method saveStudent() and status CREATE");
        StudentDto request = TEST_STORE_STUDENT_1;
        StudentDto response = request;

        mockMvc.perform(post(BASE_URL + "/students/student")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonParser.getJsonObject(request)))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(jsonParser.getJsonObject(response)))
                .andExpect(status().isCreated())
                .andReturn();
    }

    @Test
    public void shouldBeStatusOkAndReturnUpdateStudentProperly() throws Exception {

        log.info("Update a student and return student dto without id for the method updateStudent() and status OK");

        StudentDto response = studentMapper.studentToStudentDto(UPDATE_STUDENT);

        mockMvc.perform(put(BASE_URL + "/students/student/" + TEST_STUDENT_2.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonParser.getJsonObject(response)))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(jsonParser.getJsonObject(response)))
                .andExpect(status().isOk())
                .andReturn();

    }


    @Test
    public void shouldStatusBeOkAndReturnDeleteStudentProperly() throws Exception {

        log.info("Remove a student by its id from the method deleteStudentById() and status OK");

        mockMvc.perform(delete(BASE_URL + "/students/student/" + TEST_STUDENT_2.getId()))
                    .andDo(print())
                    .andExpect(status().isNoContent())
                    .andReturn();
    }
}