package by.itechart.service.schoolClass;

import by.itechart.model.SchoolClass;
import by.itechart.repository.SchoolClassRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional
    @CachePut(cacheNames = "schoolClass")
    public SchoolClass saveSchoolClass(SchoolClass schoolClass) {

        validateParams(schoolClass);

        log.info("Save a new school class with a name = {}",
                                                            schoolClass.getName());
        SchoolClass storedClass = classRepository.save(schoolClass);

        return storedClass;
    }

    @Override
    @Cacheable(cacheNames = "schoolClass", key = "#classId")
    public SchoolClass getSchoolClassById(Long classId, String username) throws Throwable {

        validateParams(classId, username);

        log.info("Receive a school class by Id = {}",
                                                     classId);
        Optional<SchoolClass> possibleSchoolClass = classRepository.getSchoolClassByIdAndUser_Username(classId, username);
        SchoolClass validSchoolClass = validateOptional(possibleSchoolClass, SchoolClass.class);

        return validSchoolClass;
    }

    @Override
    public SchoolClass getSchoolClassByName(String name, String username) throws Throwable {

        validateParams(name, username);

        log.info("Receive a school class with the name = {}",
                                                            name);
        Optional<SchoolClass> possibleSchoolClass = classRepository.getSchoolClassByNameAndUser_Username(name, username);
        SchoolClass validSchoolClass = validateOptional(possibleSchoolClass, SchoolClass.class);

        return validSchoolClass;
    }

    @Override
    @Transactional
    @CachePut(cacheNames = "schoolClass", key = "#classId")
    public SchoolClass updateSchoolClass(Long classId, SchoolClass updateSchoolClass, String username) throws Throwable {

        validateParams(classId, updateSchoolClass);

        log.info("Update a school class with ID = {}",
                                                      classId);
        SchoolClass schoolClassById = getSchoolClassById(classId, username);
        updateSchoolClass.setId(schoolClassById.getId());

        SchoolClass storedClass = classRepository.save(updateSchoolClass);

        return storedClass;
    }

    @Override
    @Transactional
    @CacheEvict(cacheNames = "schoolClass")
    public void deleteSchoolClassById(Long classId, String username) {

        validateParams(classId, username);

        log.info("Remove a school class with ID = {}",
                                                      classId);
        classRepository.deleteSchoolClassByIdAndUserUsername(classId, username);
    }

    @Override
    @Transactional
    @CacheEvict(cacheNames = "schoolClass")
    public void deleteSchoolClassByName(String name, String username) {

        validateParams(name, username);

        log.info("Remove a school class with the name = {}",
                                                            name);
        classRepository.deleteSchoolClassByNameAndUserUsername(name, username);
    }

    @Override
    public List<SchoolClass> getAllSchoolClasses(String username, Pageable pageable) {

        validateParams(username, pageable);

        log.info("Receive a list of all school classes");

        List schoolClassesByUsername = classRepository.getSchoolClassesByUser_Username(username, pageable);

        return schoolClassesByUsername;
    }
}
