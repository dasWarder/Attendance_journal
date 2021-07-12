package by.itechart.mapping.schoolClass;


import by.itechart.mapping.dto.schoolClass.SchoolClassDto;
import by.itechart.model.SchoolClass;
import by.itechart.model.Student;
import by.itechart.model.user.User;
import by.itechart.repository.SchoolClassRepository;
import by.itechart.repository.UserRepository;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static by.itechart.model.util.ValidationUtil.validateOptional;

@Slf4j
@NoArgsConstructor
@Mapper(componentModel = "spring")
public abstract class SchoolClassMapperWithUser {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SchoolClassRepository classRepository;

    public SchoolClass schoolClassDtoToSchoolClassForCreate(SchoolClassDto dto, String username) throws Throwable {

        log.info("Mapping a school class dto to the school class object for CREATE method");

        SchoolClass schoolClass = createSchoolClassSetItWithoutStudentsAndReturn(dto, username);
        schoolClass.setStudents(new HashSet<>());

        return schoolClass;
    }

    public SchoolClass schoolClassDtoToSchoolClassForUpdate(Long classId, SchoolClassDto dto, String username) throws Throwable {

        log.info("Mapping a school class dto to the school class object for Update method");

        SchoolClass schoolClass = createSchoolClassSetItWithoutStudentsAndReturn(dto, username);
        Set<Student> students = getStudentList(classId, username);
        schoolClass.setStudents(students);

        return schoolClass;
    }

    private SchoolClass createSchoolClassSetItWithoutStudentsAndReturn(SchoolClassDto dto, String username) throws Throwable {

        User user = getUserFromDbByUsername(username);
        SchoolClass schoolClass = new SchoolClass();

        schoolClass.setId(dto.getId());
        schoolClass.setName(dto.getName());
        schoolClass.setUser(user);

        return schoolClass;
    }

    private User getUserFromDbByUsername(String username) throws Throwable {

        Optional<User> possibleUser = userRepository.getUserByUsername(username);
        User user = validateOptional(possibleUser, User.class);

        return user;
    }

    private Set<Student> getStudentList(Long classId, String username) throws Throwable {

        Optional<SchoolClass> possibleClass = classRepository.getSchoolClassByIdAndUser_Username(classId, username);
        SchoolClass validClass = validateOptional(possibleClass, SchoolClass.class);
        Set<Student> students = validClass.getStudents();

        return students;
    }

}
