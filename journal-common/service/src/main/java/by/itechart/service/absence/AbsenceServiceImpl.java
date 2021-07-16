package by.itechart.service.absence;

import by.itechart.model.Absence;
import by.itechart.repository.AbsenceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

import static by.itechart.model.util.ValidationUtil.validateOptional;
import static by.itechart.model.util.ValidationUtil.validateParams;

@Slf4j
@Service
@RequiredArgsConstructor
public class AbsenceServiceImpl implements AbsenceService {

    private final AbsenceRepository absenceRepository;

    @Override
    public Absence createAbsence(Absence absence) {

        validateParams(absence);

        log.info("Store absence");

        Absence storedAbsence = absenceRepository.save(absence);

        return storedAbsence;
    }

    @Override
    public Absence getAbsenceByAbsenceDate(LocalDate absenceDate) throws Throwable {

        validateParams(absenceDate);

        log.info("Receive absence by date = {}",
                                                absenceDate);

        Optional<Absence> possibleAbsenceByDate = absenceRepository.getAbsenceByAbsenceDate(absenceDate);
        Absence validAbsenceByDate = validateOptional(possibleAbsenceByDate, Absence.class);

        return validAbsenceByDate;
    }

    @Override
    public Absence getAbsenceById(Long absenceId) throws Throwable {

        validateParams(absenceId);

        log.info("Receive absence by ID = {}",
                                             absenceId);

        Optional<Absence> possibleAbsenceById = absenceRepository.getAbsenceById(absenceId);
        Absence validAbsenceById = validateOptional(possibleAbsenceById, Absence.class);

        return validAbsenceById;
    }

    @Override
    public void deleteAbsenceByAbsenceDate(LocalDate absenceDate) {

        validateParams(absenceDate);

        log.info("Delete absence by absence date = {}",
                                                      absenceDate);

        absenceRepository.deleteAbsenceByAbsenceDate(absenceDate);
    }

    @Override
    public void deleteAbsenceById(Long absenceId) {

        validateParams(absenceId);

        log.info("Delete absence by ID = {}",
                                            absenceId);

        absenceRepository.deleteAbsenceById(absenceId);
    }

    @Override
    public Absence updateAbsence(Long absenceId, Absence updateAbsence) throws Throwable {

        validateParams(absenceId, updateAbsence);

        log.info("Update absence with ID = {}",
                                               absenceId);

        Absence absenceById = this.getAbsenceById(absenceId);
        updateAbsence.setId(absenceById.getId());

        Absence updatedAbsence = absenceRepository.save(updateAbsence);

        return updatedAbsence;
    }
}
