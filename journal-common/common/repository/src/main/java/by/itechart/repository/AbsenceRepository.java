package by.itechart.repository;

import by.itechart.model.Absence;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface AbsenceRepository extends CrudRepository<Absence, Long> {

    Optional<Absence> getAbsenceById(Long absenceId);

    Optional<Absence> getAbsenceByAbsenceDate(LocalDate absenceDate);

    void deleteAbsenceByAbsenceDate(LocalDate absenceDate);

    void deleteAbsenceById(Long absenceId);

}
