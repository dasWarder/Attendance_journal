package by.itechart.service.schoolClass;

import by.itechart.model.SchoolClass;
import by.itechart.repository.SchoolClassRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static by.itechart.model.util.ValidationUtil.validateOptional;
import static by.itechart.model.util.ValidationUtil.validateParams;

@Slf4j
@Service
@RequiredArgsConstructor
public class SchoolClassServiceImpl implements SchoolClassService {

    private final SchoolClassRepository classRepository;


    @Override
    public SchoolClass saveSchoolClass(SchoolClass schoolClass) {

        validateParams(schoolClass);

        log.info("Save a new school class with a name = {}",
                                                            schoolClass.getName());
        SchoolClass storedClass = classRepository.save(schoolClass);

        return storedClass;
    }

    @Override
    public SchoolClass getSchoolClassById(Long classId) throws Throwable {

        validateParams(classId);

        log.info("Receive a school class by Id = {}",
                                                     classId);
        Optional<SchoolClass> possibleSchoolClass = classRepository.findById(classId);
        SchoolClass validSchoolClass = validateOptional(possibleSchoolClass, SchoolClass.class);

        return validSchoolClass;
    }

    @Override
    public SchoolClass getSchoolClassByName(String name) throws Throwable {

        validateParams(name);

        log.info("Receive a school class with the name = {}",
                                                            name);
        Optional<SchoolClass> possibleSchoolClass = classRepository.getSchoolClassByName(name);
        SchoolClass validSchoolClass = validateOptional(possibleSchoolClass, SchoolClass.class);

        return validSchoolClass;
    }

    @Override
    public SchoolClass updateSchoolClass(Long classId, SchoolClass updateSchoolClass) throws Throwable {

        validateParams(classId, updateSchoolClass);

        log.info("Update a school class with ID = {}",
                                                      classId);
        SchoolClass schoolClassById = getSchoolClassById(classId);
        updateSchoolClass.setId(schoolClassById.getId());

        SchoolClass storedClass = classRepository.save(updateSchoolClass);

        return storedClass;
    }

    @Override
    public void deleteSchoolClassById(Long classId) {

        validateParams(classId);

        log.info("Remove a school class with ID = {}",
                                                      classId);
        classRepository.deleteById(classId);
    }

    @Override
    public void deleteSchoolClassByName(String name) {

        validateParams(name);

        log.info("Remove a school class with the name = {}",
                                                            name);
        classRepository.deleteSchoolClassByName(name);
    }

    @Override
    public List<SchoolClass> getAllSchoolClasses() {

        log.info("Receive a list of all school classes");

        List schoolClasses = (List) classRepository.findAll();

        return schoolClasses;
    }
}
