package by.itechart.repository;

import by.itechart.model.SchoolClass;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface SchoolClassRepository extends CrudRepository<SchoolClass, Long> {

    Optional<SchoolClass> getSchoolClassByName(String name);

    @Transactional
    void deleteSchoolClassByName(String name);

}
