package by.itechart.web.controller.studentDetails;


import by.itechart.mapping.dto.studentDetails.StudentDetailsDto;
import by.itechart.mapping.studentDetails.StudentDetailsMapper;
import by.itechart.model.StudentDetails;
import by.itechart.service.studentDetails.StudentDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/classes/class/{classId}/students/student/{studentId}/details")
public class StudentDetailsController {

    private static final String BASE_URI = "/classes/class/%d/students/student/%d/details";

    private final StudentDetailsService detailsService;

    private final StudentDetailsMapper mapper;

    @PostMapping
    public ResponseEntity<StudentDetailsDto> saveStudentDetails(@PathVariable("classId") Long classId, @PathVariable("studentId") Long studentId,
                                             @RequestBody @Valid StudentDetailsDto detailsDto) throws Throwable {

        StudentDetails studentDetails = mapper.studentDetailsDtoToStudentDetails(detailsDto);
        StudentDetails storedDetails = detailsService.saveStudentDetails(classId, studentId, studentDetails);
        StudentDetailsDto responseDto = mapper.studentDetailsToStudentDetailsDto(storedDetails);

        return ResponseEntity.created(URI.create(
                            String.format(BASE_URI + "/%d", classId, studentId, studentDetails.getId())))
                            .body(responseDto);
    }

    @GetMapping
    public ResponseEntity<StudentDetailsDto> getStudentDetails(@PathVariable("classId") Long classId,
                                            @PathVariable("studentId") Long studentId) throws Throwable {

        StudentDetails validDetails = detailsService.getStudentDetailsBySchoolClassIdAndStudentId(classId, studentId);
        StudentDetailsDto responseDto = mapper.studentDetailsToStudentDetailsDto(validDetails);

        return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteStudentDetails(@PathVariable("classId") Long classId,
                                               @PathVariable("studentId") Long studentId) {

        detailsService.deleteStudentDetailsBySchoolClassIdAndStudentId(classId, studentId);

        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity<StudentDetailsDto> updateStudentDetails(@PathVariable("classId") Long classId,
                                                                  @PathVariable("studentId") Long studentId,
                                                                  @RequestBody @Valid StudentDetailsDto detailsDto) throws Throwable {

        StudentDetails requestDetails = mapper.studentDetailsDtoToStudentDetails(detailsDto);
        StudentDetails updatedDetails = detailsService.updateStudentDetails(classId, studentId, requestDetails);
        StudentDetailsDto responseDto = mapper.studentDetailsToStudentDetailsDto(updatedDetails);

        return ResponseEntity.ok(responseDto);
    }
}
