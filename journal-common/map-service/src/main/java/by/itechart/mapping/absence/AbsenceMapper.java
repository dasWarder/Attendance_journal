package by.itechart.mapping.absence;

import by.itechart.mapping.dto.absence.AbsenceDto;
import by.itechart.model.Absence;
import by.itechart.model.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface AbsenceMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "students", ignore = true)
    Absence absenceDtoToAbsence(AbsenceDto absenceDto);

    AbsenceDto absenceToAbsenceDto(Absence absence);

    Set<AbsenceDto> absenceSetToAbsenceDtoSet(Set<Student> students);
}
