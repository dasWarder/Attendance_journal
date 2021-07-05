package by.itechart.web.integration.mapping;


import by.itechart.mapping.student.StudentMapping;
import by.itechart.model.Student;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static by.itechart.web.integration.mapping.StudentMappingTestData.*;
import static org.assertj.core.api.Assertions.assertThat;


@Slf4j
@SpringBootTest
@ExtendWith(SpringExtension.class)
@Sql(scripts = {"/db/student/init.sql" , "/db/student/populate.sql"})
public class StudentMappingServiceIntegrationTest {

      @Autowired
      private StudentMapping  studentMapping;


      @Test
      public void shouldMapStudentDtoToStudentProperly() throws Throwable {

            log.info("Mapping from a Student dto to the student");
            Student student = studentMapping.fromStudentDtoToStudent(TEST_DTO_1, TEST_SCHOOL_CLASS_1.getId());

            assertThat(student)
                              .usingRecursiveComparison()
                              .ignoringFields("schoolClass")
                              .isEqualTo(TEST_STUDENT_1);
      }
}
