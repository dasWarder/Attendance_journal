package by.itechart.mapping.schoolClass;


import by.itechart.mapping.dto.schoolClass.SchoolClassDto;
import by.itechart.model.SchoolClass;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SchoolClassMapper {

    SchoolClassDto schoolClassToSchoolClassDto(SchoolClass schoolClass);

    List<SchoolClassDto> schoolClassListToSchoolClassDtoList(List<SchoolClass> schoolClasses);
}
