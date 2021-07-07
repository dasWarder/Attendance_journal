package by.itechart.repository;

import by.itechart.model.SchoolClass;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface SchoolClassRepository extends CrudRepository<SchoolClass, Long> {

    Optional<SchoolClass> getSchoolClassByName(String name);

    void deleteSchoolClassByName(String name);

}
