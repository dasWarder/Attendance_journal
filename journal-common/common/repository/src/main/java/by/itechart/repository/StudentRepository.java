package by.itechart.repository;

import by.itechart.model.Student;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


public interface StudentRepository extends CrudRepository<Student, Long> {

    List<Student> findAllBySchoolClass_Id(Long classId);

    Optional<Student> getStudentByIdAndSchoolClass_Id(Long studentId, Long classId);

    @Transactional
    void deleteStudentByIdAndSchoolClass_Id(Long studentId, Long classId);

}
