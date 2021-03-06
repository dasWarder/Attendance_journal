package by.itechart.web.controller.attending;


import by.itechart.mapping.dto.student.StudentDto;
import by.itechart.mapping.dto.student.StudentDtoId;
import by.itechart.mapping.student.StudentMapper;
import by.itechart.mapping.student.StudentMapperWithSchoolClass;
import by.itechart.model.Student;
import by.itechart.service.attending.StudentAbsenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/classes/class/{classId}/absence")
@RequiredArgsConstructor
public class StudentAbsenceController {

    private final StudentMapper mapper;

    private final StudentMapperWithSchoolClass customMapper;

    private final StudentAbsenceService studentAbsenceService;


    @GetMapping
    public ResponseEntity<List<StudentDtoId>> getClassAbsenceByDate(@PathVariable("classId")
                                                                    @Min(value = 1,
                                                                         message = "The ID must be greater that 0")
                                                                    Long classId,
                                                                    @RequestParam("date")
                                                                    @DateTimeFormat(iso =
                                                                            DateTimeFormat.ISO.DATE)
                                                                    LocalDate absenceDate,
                                                                    @PageableDefault(page = 0, size = 25, sort = { "id" })
                                                                    Pageable pageable) throws Throwable {

        List<Student> allStudentByDateAndClassId = studentAbsenceService
                                                         .getAllByAbsenceDatesAndSchoolClassId(absenceDate, classId, pageable);
        List<StudentDtoId> studentDtoAbsence = mapper.studentListToStudentDtoIdList(allStudentByDateAndClassId);

        return ResponseEntity.ok(studentDtoAbsence);
    }

    @PostMapping("/{studentId}")
    public ResponseEntity<StudentDto> addStudentToAbsenceList(@PathVariable("classId")
                                                              @Min(value = 1,
                                                                   message = "The ID must be greater that 0")
                                                              Long classId,
                                                              @PathVariable("studentId")
                                                              @Min(value = 1,
                                                                   message = "The ID must be greater that 0")
                                                              Long studentId,
                                                              @RequestParam("date")
                                                              @DateTimeFormat(iso =
                                                                              DateTimeFormat.ISO.DATE)
                                                              LocalDate absenceDate) throws Throwable {

        Student storedAbsenceStudent = studentAbsenceService.addStudentToAbsenceList(classId, studentId, absenceDate);
        StudentDto responseStudentDto = mapper.studentToStudentDto(storedAbsenceStudent);

        return ResponseEntity.created(URI.create("/classes/class/" + classId + "/absence/" + studentId))
                                                                                .body(responseStudentDto);
    }

    @PostMapping
    public ResponseEntity<List<StudentDto>> saveAbsenceList(@PathVariable("classId")
                                                           @Min(value = 1,
                                                                       message = "The ID must be greater that 0")
                                                           Long classId,
                                                           @RequestBody
                                                           @Valid List<StudentDtoId> dtoSet,
                                                           @RequestParam("date")
                                                           @DateTimeFormat(iso =
                                                                   DateTimeFormat.ISO.DATE)
                                                                   LocalDate absenceDate) throws Throwable {

        List<Student> students = customMapper.studentDtoIdListToStudentList(dtoSet, classId);
        List<Student> storedStudents = studentAbsenceService.addStudentsToAbsenceList(students, absenceDate);
        List<StudentDto> responseDtoOfAbsenceStudents = mapper.studentListToStudentDtoList(storedStudents);

        return ResponseEntity.created(URI.create("/classes/class/" + classId + "/absence"))
                                                                    .body(responseDtoOfAbsenceStudents);
    }

    @DeleteMapping("/{studentId}")
    public ResponseEntity<Void> deleteStudentFromAbsenceList(@PathVariable("classId")
                                                             @Min(value = 1,
                                                                     message = "The ID must be greater that 0")
                                                             Long classId,
                                                             @PathVariable("studentId")
                                                             @Min(value = 1,
                                                                     message = "The ID must be greater that 0")
                                                                     Long studentId,
                                                             @RequestParam("date")
                                                             @DateTimeFormat(iso =
                                                                     DateTimeFormat.ISO.DATE)
                                                                     LocalDate absenceDate) throws Throwable {

        studentAbsenceService.deleteStudentFromAbsenceList(classId, studentId, absenceDate);

        return ResponseEntity.noContent()
                                         .build();
    }
}
