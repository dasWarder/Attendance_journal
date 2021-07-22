package by.itechart.web.controller.schoolClass;


import by.itechart.mapping.dto.schoolClass.SchoolClassDto;
import by.itechart.mapping.schoolClass.SchoolClassMapper;
import by.itechart.mapping.schoolClass.SchoolClassMapperWithUser;
import by.itechart.model.SchoolClass;
import by.itechart.service.schoolClass.SchoolClassService;
import by.itechart.web.security.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/classes")
public class SchoolClassController {

    private final SecurityUtil securityUtil;

    private final SchoolClassService classService;

    private final SchoolClassMapper classMapper;

    private final SchoolClassMapperWithUser customMapper;

    @PostMapping("/class")
    public ResponseEntity<SchoolClassDto> saveSchoolClass(@RequestBody
                                                          @Valid SchoolClassDto schoolClassDto) throws Throwable {

        SchoolClass schoolClass = customMapper.schoolClassDtoToSchoolClassForCreate(schoolClassDto, securityUtil.getLoggedUser());
        SchoolClass storedSchoolClass = classService.saveSchoolClass(schoolClass);
        SchoolClassDto dto = classMapper.schoolClassToSchoolClassDto(storedSchoolClass);

        return ResponseEntity.created(URI.create("/classes/class/" + dto.getId())).body(dto);
    }

    @GetMapping("/class/{classId}")
    public ResponseEntity<SchoolClassDto> getSchoolClassById(@PathVariable("classId")
                                                             @Min(value = 1,
                                                                  message = "The ID must be greater that 0")
                                                             @NotNull(message = "The ID of a class must be not NULL")
                                                             Long classId)
                                                                           throws Throwable {

        SchoolClass validSchoolClass = classService.getSchoolClassById(classId, securityUtil.getLoggedUser());
        SchoolClassDto dto = classMapper.schoolClassToSchoolClassDto(validSchoolClass);

        return ResponseEntity.ok(dto);
    }

    @GetMapping("/class")
    public ResponseEntity<SchoolClassDto> getSchoolClassByName(@RequestParam(value = "name")
                                                               @Min(value = 1,
                                                                    message = "The size of the name must be greater than 1")
                                                               String name)
                                                                           throws Throwable {

        SchoolClass validSchoolClass = classService.getSchoolClassByName(name, securityUtil.getLoggedUser());
        SchoolClassDto dto = classMapper.schoolClassToSchoolClassDto(validSchoolClass);

        return ResponseEntity.ok(dto);
    }

    @PutMapping("/class/{classId}")
    public ResponseEntity<SchoolClassDto> updateSchoolClass(@PathVariable("classId")
                                                            @Min(value = 1,
                                                                 message = "The ID must be greater that 0")
                                                            Long classId,
                                                            @RequestBody
                                                            @Valid SchoolClassDto schoolClassDto)
                                                                                                throws Throwable {

        String username = securityUtil.getLoggedUser();

        SchoolClass schoolClass = customMapper.schoolClassDtoToSchoolClassForUpdate(classId, schoolClassDto, username);
        SchoolClass updatedSchoolClass = classService.updateSchoolClass(classId, schoolClass, username);
        SchoolClassDto dto = classMapper.schoolClassToSchoolClassDto(updatedSchoolClass);

        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/class/{classId}")
    public ResponseEntity<Void> deleteSchoolClassById(@PathVariable("classId")
                                                        @Min(value = 1,
                                                             message = "The ID must be greater that 0")
                                                        Long classId) {

        classService.deleteSchoolClassById(classId, securityUtil.getLoggedUser());

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/class")
    public ResponseEntity<Void> deleteSchoolClassByName(@RequestParam("name")
                                                          @Min(value = 1,
                                                               message = "The size of the name must be greater than 1")
                                                          String name) {

        classService.deleteSchoolClassByName(name, securityUtil.getLoggedUser());

        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<SchoolClassDto>> getAllSchoolClasses(@PageableDefault(page = 0, size = 25, sort = { "id" })
                                                                    Pageable pageable) {

        List<SchoolClass> schoolClasses = classService.getAllSchoolClasses(securityUtil.getLoggedUser(), pageable);
        List<SchoolClassDto> dtoList = classMapper.schoolClassListToSchoolClassDtoList(schoolClasses);

        return ResponseEntity.ok(dtoList);
    }

}
