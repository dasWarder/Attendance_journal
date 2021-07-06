package by.itechart.service.schoolClass;

import by.itechart.model.SchoolClass;
import by.itechart.model.util.ValidationUtil;
import by.itechart.repository.SchoolClassRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static by.itechart.model.util.ValidationUtil.validateOptional;
import static by.itechart.model.util.ValidationUtil.validateParams;

@Slf4j
@Service
@RequiredArgsConstructor
public class SchoolClassServiceImpl implements SchoolClassService {

    private final SchoolClassRepository classRepository;

    @Override
    public SchoolClass getSchoolClassById(Long classId) throws Throwable {

        validateParams(classId);

        log.info("Receive a school class by Id = {}",
                                                     classId);

        Optional<SchoolClass> possibleSchoolClass = classRepository.findById(classId);
        SchoolClass validSchoolClass = validateOptional(possibleSchoolClass, SchoolClass.class);

        return validSchoolClass;
    }
}
