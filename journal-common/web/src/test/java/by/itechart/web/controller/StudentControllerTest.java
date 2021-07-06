package by.itechart.web.controller;

import by.itechart.mapping.dto.StudentDto;
import by.itechart.mapping.dto.StudentDtoId;
import by.itechart.mapping.student.StudentMapper;
import by.itechart.model.Student;
import by.itechart.web.controller.util.JsonParser;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static by.itechart.web.data.StudentTestData.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@Sql(scripts = { "/db/student/init.sql", "/db/student/populate.sql" })
class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JsonParser jsonParser;

    @Autowired
    private StudentMapper studentMapper;

    private static final String BASE_URL = "/classes/class/1";

    @Test
    public void shouldBeStatusOkAndReturnAllStudentsProperly() throws Exception {

        log.info("Receive a list of Student dto with id from method findAllStudents() and status OK");

        List<Student> studentsOrigin = List.of(TEST_STUDENT_2, TEST_STUDENT_3, TEST_STUDENT_4);
        List<StudentDtoId> students = studentMapper.map(studentsOrigin);

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
        StudentDto response = TEST_STORE_STUDENT_1;

        mockMvc.perform(post(BASE_URL + "/students/student")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonParser.getJsonObject(TEST_STORE_STUDENT_1)))
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
        String response = String.format(
                                "The student with ID = %d for a school class with ID = %d was successfully removed",
                                                                                                              TEST_STUDENT_2.getId(),
                                                                                                                                   1);
        mockMvc.perform(delete(BASE_URL + "/students/student/" + TEST_STUDENT_2.getId()))
                .andDo(print())
                .andExpect(content().string(response))
                .andExpect(status().isOk())
                .andReturn();
    }
}