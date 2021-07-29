package by.itechart.repository;

import by.itechart.model.StudentDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentDetailsRepository extends JpaRepository<StudentDetails, Long> {

    Optional<StudentDetails> getStudentDetailsByStudentSchoolClassIdAndStudentId(Long classId, Long studentId);

    void deleteStudentDetailsByStudentSchoolClassIdAndStudentId(Long classId, Long studentId);
}
