package by.itechart.service.attending;

import by.itechart.model.Student;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface StudentAbsenceService {

    List<Student> getAllByAbsenceDatesAndSchoolClassId(LocalDate absenceDate, Long classId) throws Throwable;

    Student addStudentToAbsenceList(Long classId, Long studentId, LocalDate absenceDate) throws Throwable;

    void deleteStudentFromAbsenceList(Long classId, Long studentId, LocalDate absenceDate) throws Throwable;

    Set<Student> addStudentsToAbsenceList(Set<Student> students, LocalDate absenceDate) throws Throwable;
}
