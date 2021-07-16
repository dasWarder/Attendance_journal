package by.itechart.web.controller.attending;


import by.itechart.mapping.dto.student.StudentDtoId;
import by.itechart.mapping.student.StudentMapper;
import by.itechart.model.Student;
import by.itechart.service.attending.StudentAbsenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/classes/class/{classId}/absence")
@RequiredArgsConstructor
public class StudentAbsenceController {

    private final StudentMapper mapper;

    private final StudentAbsenceService studentAbsenceService;


    @GetMapping
    public ResponseEntity<List<StudentDtoId>> getClassAbsenceByDate(@PathVariable("classId") Long classId,
                                                @RequestParam("date")
                                                @DateTimeFormat(iso =
                                                        DateTimeFormat.ISO.DATE)
                                                LocalDate absenceDate) throws Throwable {

        List<Student> allStudentByDateAndClassId =
                                                studentAbsenceService.getAllByAbsenceDatesAndSchoolClassId(
                                                                                                            absenceDate,
                                                                                                            classId);

        List<StudentDtoId> studentDtoAbsence = mapper.studentListToStudentDtoIdList(allStudentByDateAndClassId);

        return new ResponseEntity(
                                  studentDtoAbsence, HttpStatus.OK);
    }
}
