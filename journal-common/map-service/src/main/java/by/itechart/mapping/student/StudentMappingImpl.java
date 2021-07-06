package by.itechart.mapping.student;

import by.itechart.mapping.dto.StudentDto;
import by.itechart.mapping.dto.StudentDtoId;
import by.itechart.model.SchoolClass;
import by.itechart.model.Student;
import by.itechart.repository.SchoolClassRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static by.itechart.model.util.ValidationUtil.validateOptional;

@Slf4j
@Service
public class StudentMappingImpl implements StudentMapping {


    private final SchoolClassRepository classRepository;

    public StudentMappingImpl(SchoolClassRepository classRepository) {
        this.classRepository = classRepository;
    }

    @Override
    public Student fromStudentDtoToStudent(StudentDto dto, Long classId) throws Throwable {

        log.info("Mapping from dto with surname = {} to a student object",
                                                                         dto.getSurname());

        Optional<SchoolClass> schoolClassById = classRepository.findById(classId);

        SchoolClass schoolClass = validateOptional(schoolClassById, SchoolClass.class);


        Student student = Student.builder()
                                    .name(dto.getName())
                                    .surname(dto.getSurname())
                                    .schoolClass(schoolClass)
                                    .build();

        return student;
    }

    @Override
    public StudentDto fromStudentToStudentDto(Student student) {

        log.info("Mapping from the student with ID ={} to a student dto",
                                                                         student.getId());
        StudentDto dto = new StudentDto(
                                        student.getName(), student.getSurname());

        return dto;
    }

    @Override
    public List<StudentDtoId> fromStudentListToStudentDtoIdList(List<Student> students) {

        log.info("Mapping from a list of students to a list of student dto");
        List<StudentDtoId> dtoList = new ArrayList<>(students.size());

        students.forEach(student -> {

            StudentDtoId dto = new StudentDtoId(
                                                student.getId(),
                                                student.getName(),
                                                student.getSurname());
            dtoList.add(dto);
        });

        return dtoList;
    }


}
