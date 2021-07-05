package by.itechart.service.student;

import by.itechart.mapping.student.StudentMapping;
import by.itechart.mapping.student.StudentMappingImpl;
import by.itechart.repository.SchoolClassRepository;
import by.itechart.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class StudentServiceTest {

    private StudentRepository studentRepository = Mockito.mock(StudentRepository.class);

    private SchoolClassRepository schoolClassRepository = Mockito.mock(SchoolClassRepository.class);

    private StudentService studentService = new StudentServiceImpl(studentRepository);


    @Test
    public void test() {

    }

}