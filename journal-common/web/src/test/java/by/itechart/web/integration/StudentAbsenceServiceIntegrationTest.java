package by.itechart.web.integration;


import by.itechart.service.attending.StudentAbsenceService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@Slf4j
@SpringBootTest
@ExtendWith(SpringExtension.class)
@Sql(scripts = { "/db/student/populate.sql" })
public class StudentAbsenceServiceIntegrationTest {

    @Autowired
    private StudentAbsenceService studentAbsenceService;


    @Test
    public void test() {

    }
}
