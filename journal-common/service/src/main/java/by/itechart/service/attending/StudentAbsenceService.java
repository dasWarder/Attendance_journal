package by.itechart.service.attending;

import by.itechart.model.Student;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

public interface StudentAbsenceService {

    List<Student> getAllByAbsenceDatesAndSchoolClassId(LocalDate absenceDate, Long classId, Pageable pageable) throws Throwable;

    Student addStudentToAbsenceList(Long classId, Long studentId, LocalDate absenceDate) throws Throwable;

    void deleteStudentFromAbsenceList(Long classId, Long studentId, LocalDate absenceDate) throws Throwable;

    List<Student> addStudentsToAbsenceList(List<Student> students, LocalDate absenceDate);
}
