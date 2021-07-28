package by.itechart.repository;

import by.itechart.model.SchoolClass;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

public interface SchoolClassRepository extends PagingAndSortingRepository<SchoolClass, Long> {

    Optional<SchoolClass> getSchoolClassByNameAndUser_Username(String name, String username);

    Optional<SchoolClass> getSchoolClassByIdAndUser_Username(Long classId, String username);

    void deleteSchoolClassByNameAndUserUsername(String name, String username);

    void deleteSchoolClassByIdAndUserUsername(Long classId, String name);

    List<SchoolClass> getSchoolClassesByUser_Username(String username, Pageable pageable);

}
