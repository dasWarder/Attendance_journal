package by.itechart.repository;

import by.itechart.model.SchoolClass;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface SchoolClassRepository extends PagingAndSortingRepository<SchoolClass, Long> {

    List<SchoolClass> getSchoolClassesByUser_Username(String username);

    Optional<SchoolClass> getSchoolClassByNameAndUser_Username(String name, String username);

    Optional<SchoolClass> getSchoolClassByIdAndUser_Username(Long classId, String username);
    @Transactional
    void deleteSchoolClassByNameAndUserUsername(String name, String username);

    @Transactional
    void deleteSchoolClassByIdAndUserUsername(Long classId, String name);

    List<SchoolClass> getSchoolClassesByUser_Username(String username, Pageable pageable);

}
