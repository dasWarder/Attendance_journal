package by.itechart.repository;

import by.itechart.model.Student;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;


public interface StudentRepository extends PagingAndSortingRepository<Student, Long> {

    List<Student> findAllBySchoolClassId(Long classId, Pageable pageable);

    Optional<Student> getStudentByIdAndSchoolClassId(Long studentId, Long classId);

    void deleteStudentByIdAndSchoolClassId(Long studentId, Long classId);

    List<Student> findAllBySchoolClassId(Long classId);

}
