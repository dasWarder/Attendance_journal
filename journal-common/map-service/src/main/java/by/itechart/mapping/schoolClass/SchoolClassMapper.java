package by.itechart.mapping.schoolClass;


import by.itechart.mapping.dto.schoolClass.SchoolClassDto;
import by.itechart.model.SchoolClass;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SchoolClassMapper {

    SchoolClassMapper INSTANCE = Mappers.getMapper( SchoolClassMapper.class );

    @Mapping(target = "students", ignore = true)
    SchoolClass schoolClassDtoToSchoolClass(SchoolClassDto dto);

    SchoolClassDto schoolClassToSchoolClassDto(SchoolClass schoolClass);

    List<SchoolClassDto> schoolClassListToSchoolClassDtoList(List<SchoolClass> schoolClasses);
}
