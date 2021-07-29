package by.itechart.web.controller.student;

import by.itechart.mapping.dto.student.StudentDto;
import by.itechart.mapping.dto.student.StudentDtoId;
import by.itechart.mapping.student.StudentMapper;
import by.itechart.mapping.student.StudentMapperWithSchoolClass;
import by.itechart.model.Student;
import by.itechart.service.student.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.net.URI;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/classes/class/{classId}/students")
public class StudentController {

    private final StudentService studentService;

    private final StudentMapper studentMapper;

    private final StudentMapperWithSchoolClass customStudentMapper;

    private static final String BASE_URL = "/classes/class/%d/students/student/%d";

    @PostMapping("/student")
    public ResponseEntity<StudentDto> saveStudent(@RequestBody
                                                  @Valid StudentDto studentDto,
                                                  @PathVariable("classId")
                                                  @Min(value = 1,
                                                          message = "The ID must be greater that 0")
                                                  Long classId)
                                                               throws Throwable {

        Student student = customStudentMapper.studentDtoToStudent(studentDto, classId);
        Student storedStudent = studentService.saveStudent(student);
        StudentDto dto = studentMapper.studentToStudentDto(storedStudent);

        return ResponseEntity.created(URI.create(
                                                String.format(BASE_URL, classId, storedStudent.getId())))
                                                .body(dto);
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<StudentDto> getStudentById(@PathVariable("studentId")
                                                     @Min(value = 1,
                                                          message = "The ID must be greater that 0")
                                                     Long studentId,
                                                     @PathVariable("classId")
                                                     @Min(value = 1,
                                                          message = "The ID must be greater that 0")
                                                     Long classId)
                                                                 throws Throwable {

        Student studentById = studentService.getStudentByIdAndClassId(studentId, classId);
        StudentDto dto = studentMapper.studentToStudentDto(studentById);

        return ResponseEntity.ok(dto);
    }

    @PutMapping("/student/{studentId}")
    public ResponseEntity<StudentDto> updateStudent(@PathVariable("studentId")
                                                    @Min(value = 1,
                                                         message = "The ID must be greater that 0")
                                                    Long studentId,
                                                    @PathVariable("classId")
                                                    @Min(value = 1,
                                                         message = "The ID must be greater that 0")
                                                    Long classId,
                                                    @RequestBody
                                                    @Valid StudentDto studentDto)
                                                                                throws Throwable {

        Student student = customStudentMapper.studentDtoToStudent(studentDto, classId);
        Student updatedStudent = studentService.updateStudent(studentId, student, classId);
        StudentDto dto = studentMapper.studentToStudentDto(updatedStudent);

        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/student/{studentId}")
    public ResponseEntity<Void> deleteStudent(@PathVariable("studentId")
                                                @Min(value = 1,
                                                     message = "The ID must be greater that 0")
                                                Long studentId,
                                                @PathVariable("classId")
                                                @Min(value = 1,
                                                     message = "The ID must be greater that 0")
                                                Long classId) {

        studentService.deleteStudentByIdAndClassId(studentId, classId);

        return ResponseEntity.noContent()
                                        .build();
    }

    @GetMapping
    public ResponseEntity<List<StudentDtoId>> getAllStudentsByClassId(@PathVariable("classId")
                                                                      @Min(value = 1,
                                                                           message = "The ID must be greater that 0")
                                                                      Long classId,
                                                                      @PageableDefault(page = 0, size = 25, sort = { "id" })
                                                                      Pageable pageable) {

        List<Student> allStudents = studentService.findAllStudents(classId, pageable);
        List<StudentDtoId> dtoList = studentMapper.studentListToStudentDtoIdList(allStudents);

        return ResponseEntity.ok(dtoList);
    }
}
