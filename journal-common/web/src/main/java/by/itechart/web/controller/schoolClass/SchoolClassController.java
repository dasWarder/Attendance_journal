package by.itechart.web.controller.schoolClass;


import by.itechart.mapping.dto.schoolClass.SchoolClassDto;
import by.itechart.mapping.dto.student.StudentDto;
import by.itechart.mapping.dto.student.StudentDtoId;
import by.itechart.mapping.schoolClass.SchoolClassMapper;
import by.itechart.model.SchoolClass;
import by.itechart.model.Student;
import by.itechart.service.schoolClass.SchoolClassService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/classes")
public class SchoolClassController {

    private final SchoolClassService classService;

    private final SchoolClassMapper classMapper;

    private final String REMOVE_BY_ID_MESSAGE = "The school class with ID = %d was successfully removed";

    private final String REMOVE_BY_NAME_MESSAGE = "The school class with the name = %s was successfully removed";


    @PostMapping("/class")
    public ResponseEntity<SchoolClassDto> saveSchoolClass(@RequestBody
                                                          @Valid SchoolClassDto schoolClassDto) {

        SchoolClass schoolClass = classMapper.schoolClassDtoToSchoolClass(schoolClassDto);
        SchoolClass storedSchoolClass = classService.saveSchoolClass(schoolClass);
        SchoolClassDto dto = classMapper.schoolClassToSchoolClassDto(storedSchoolClass);

        return new ResponseEntity<>(
                                    dto, HttpStatus.CREATED);
    }

    @GetMapping("/class/{classId}")
    public ResponseEntity<SchoolClassDto> getSchoolClassById(@PathVariable("classId")
                                                             @Min(value = 1,
                                                                  message = "The ID must be greater that 0")
                                                             @NotNull(message = "The ID of a class must be not NULL")
                                                             Long classId)
                                                                           throws Throwable {

        SchoolClass validSchoolClass = classService.getSchoolClassById(classId);
        SchoolClassDto dto = classMapper.schoolClassToSchoolClassDto(validSchoolClass);

        return new ResponseEntity<>(
                                    dto, HttpStatus.OK);
    }

    @GetMapping("/class")
    public ResponseEntity<SchoolClassDto> getSchoolClassByName(@RequestParam(value = "name")
                                                               @Min(value = 1,
                                                                    message = "The size of the name must be greater than 1")
                                                               String name)
                                                                           throws Throwable {

        SchoolClass validSchoolClass = classService.getSchoolClassByName(name);
        SchoolClassDto dto = classMapper.schoolClassToSchoolClassDto(validSchoolClass);

        return new ResponseEntity<>(
                                    dto, HttpStatus.OK);
    }

    @PutMapping("/class/{classId}")
    public ResponseEntity<SchoolClassDto> updateSchoolClass(@PathVariable("classId")
                                                            @Min(value = 1,
                                                                 message = "The ID must be greater that 0")
                                                            Long classId,
                                                            @RequestBody
                                                            @Valid SchoolClassDto schoolClassDto)
                                                                                                throws Throwable {

        SchoolClass schoolClass = classMapper.schoolClassDtoToSchoolClass(schoolClassDto);
        SchoolClass updatedSchoolClass = classService.updateSchoolClass(classId, schoolClass);
        SchoolClassDto dto = classMapper.schoolClassToSchoolClassDto(updatedSchoolClass);

        return new ResponseEntity<>(
                                    dto, HttpStatus.OK);
    }

    @DeleteMapping("/class/{classId}")
    public ResponseEntity<String> deleteSchoolClassById(@PathVariable("classId")
                                                        @Min(value = 1,
                                                             message = "The ID must be greater that 0")
                                                        Long classId) {
        classService.deleteSchoolClassById(classId);

        return new ResponseEntity<>(
                                    String.format(REMOVE_BY_ID_MESSAGE, classId), HttpStatus.OK);
    }

    @DeleteMapping("/class")
    public ResponseEntity<String> deleteSchoolClassByName(@RequestParam("name")
                                                          @Min(value = 1,
                                                               message = "The size of the name must be greater than 1")
                                                          String name) {
        classService.deleteSchoolClassByName(name);

        return new ResponseEntity<>(
                                    String.format(REMOVE_BY_NAME_MESSAGE, name), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<SchoolClassDto>> getAllSchoolClasses() {

        List<SchoolClass> schoolClasses = classService.getAllSchoolClasses();
        List<SchoolClassDto> dtoList = classMapper.schoolClassListToSchoolClassDtoList(schoolClasses);

        return new ResponseEntity<>(
                                    dtoList, HttpStatus.OK);
    }

}
