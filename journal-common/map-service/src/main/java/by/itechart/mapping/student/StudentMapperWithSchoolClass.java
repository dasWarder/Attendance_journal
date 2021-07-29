package by.itechart.mapping.student;


import by.itechart.mapping.dto.student.StudentDto;
import by.itechart.mapping.dto.student.StudentDtoId;
import by.itechart.model.SchoolClass;
import by.itechart.model.Student;
import by.itechart.model.exception.StudentNotFoundException;
import by.itechart.repository.SchoolClassRepository;
import by.itechart.repository.StudentRepository;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static by.itechart.model.util.ValidationUtil.validateOptional;


@Slf4j
@NoArgsConstructor
@Mapper(componentModel = "spring")
public abstract class StudentMapperWithSchoolClass {

    @Autowired
    private SchoolClassRepository classRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StudentMapper studentMapper;

    public Student studentDtoToStudent(StudentDto dto, Long classId) throws Throwable {

        log.info("Mapping from a student dto to the student with the school class ID = {}",
                                                                                          classId);

        Optional<SchoolClass> possibleSchoolClass = classRepository.findById(classId);

        SchoolClass schoolClass = validateOptional(possibleSchoolClass, SchoolClass.class);

        Student student = studentMapper.studentDtoToStudent(dto);
        student.setSchoolClass(schoolClass);

        return student;
    }


    public List<Student> studentDtoIdListToStudentList(List<StudentDtoId> dtoIdSet, Long classId) throws Throwable {

        log.info("Mapping student dto id set to the student set");

        List<Student> allBySchoolClassId = studentRepository.findAllBySchoolClassId(classId);

        List<Long> collectOfIds = dtoIdSet.stream()
                                                .map(s -> s.getId())
                                                .collect(Collectors.toList());

        List<String> collectionOfNamesFromDto = dtoIdSet.stream()
                                                                .map(dto -> dto.getName() + " " + dto.getSurname())
                                                                .collect(Collectors.toList());

        List<Student> studentWithCorrectId = allBySchoolClassId.stream()
                                                                    .filter(s -> collectOfIds.contains(s.getId()))
                                                                    .filter(s -> collectionOfNamesFromDto
                                                                            .contains(s.getName() + " " + s.getSurname()))
                                                                    .collect(Collectors.toList());

      if(studentWithCorrectId.size() != dtoIdSet.size()) {

          throw new StudentNotFoundException("One or more students not found");
      }

      return studentWithCorrectId;
    }
}
