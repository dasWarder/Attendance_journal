package by.itechart.mapping.absence;

import by.itechart.mapping.dto.absence.AbsenceDto;
import by.itechart.model.Absence;
import by.itechart.repository.AbsenceRepository;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static by.itechart.model.util.ValidationUtil.validateOptional;

@Slf4j
@NoArgsConstructor
@Mapper(componentModel = "spring")
public abstract class AbsenceMapperWithId {

    @Autowired
    private AbsenceRepository absenceRepository;


    public Absence absenceDtoToAbsenceWithId(AbsenceDto dto) throws Throwable {

        log.info("Mapping absence dto to the absence with ID");

        Absence absence = validateAbsenceOrThrowException(dto);

        return absence;
    }

    private Absence validateAbsenceOrThrowException(AbsenceDto dto) throws Throwable {

        Absence absence = new Absence();

        try {

            Optional<Absence> possibleAbsence = absenceRepository.getAbsenceByAbsenceDate(dto.getAbsenceDate());
            Absence validAbsence = validateOptional(possibleAbsence, Absence.class);

            absence.setId(validAbsence.getId());
            absence.setAbsenceDate(dto.getAbsenceDate());
            absence.setStudents(validAbsence.getStudents());

            return absence;

        } catch (Throwable throwable) {

            absence.setAbsenceDate(dto.getAbsenceDate());

            return absence;
        }
    }
}
