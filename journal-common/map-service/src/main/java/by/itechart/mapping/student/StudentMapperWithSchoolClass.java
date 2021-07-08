package by.itechart.mapping.student;


import by.itechart.mapping.dto.student.StudentDto;
import by.itechart.model.SchoolClass;
import by.itechart.model.Student;
import by.itechart.repository.SchoolClassRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static by.itechart.model.util.ValidationUtil.*;


@Slf4j
@NoArgsConstructor
@Mapper(componentModel = "spring")
public abstract class StudentMapperWithSchoolClass {

    @Autowired
    private SchoolClassRepository classRepository;

    @Autowired
    private StudentMapper studentMapper;

    public Student studentDtoToStudent(StudentDto dto, Long classId) throws Throwable {

        log.info("Mapping from a student dto to the student with the school class ID = {}", classId);

        Optional<SchoolClass> possibleSchoolClass = classRepository.findById(classId);

        SchoolClass schoolClass = validateOptional(possibleSchoolClass, SchoolClass.class);

        Student student = studentMapper.studentDtoToStudent(dto);
        student.setSchoolClass(schoolClass);

        return student;
    }
}
