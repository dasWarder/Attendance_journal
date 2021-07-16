package by.itechart.service.attending;

import by.itechart.model.Student;

import java.time.LocalDate;
import java.util.List;

public interface StudentAbsenceService {

    List<Student> getAllByAbsenceDatesAndSchoolClassId(LocalDate absenceDate, Long classId) throws Throwable;
}
