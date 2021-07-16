package by.itechart.service.absence;

import by.itechart.model.Absence;

import java.time.LocalDate;

public interface AbsenceService {

    Absence createAbsence(Absence absence);

    Absence getAbsenceByAbsenceDate(LocalDate absenceDate) throws Throwable;

    Absence getAbsenceById(Long absenceId) throws Throwable;

    void deleteAbsenceByAbsenceDate(LocalDate absenceDate);

    void deleteAbsenceById(Long absenceId);

    Absence updateAbsence(Long absenceId, Absence updateAbsence) throws Throwable;
}
