package by.itechart.web.controller;

import by.itechart.mapping.dto.StudentDto;
import by.itechart.mapping.student.StudentMapping;
import by.itechart.model.Student;
import by.itechart.service.student.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/classes/class/{classId}/students")
public class StudentController {

    private final StudentService studentService;

    private final StudentMapping studentMapping;

    private final String REMOVE_MESSAGE = "The student with ID = %d for a school class with ID = %d was successfully removed";

    public StudentController(StudentService studentService, StudentMapping studentMapping) {
        this.studentService = studentService;
        this.studentMapping = studentMapping;
    }

    @PostMapping("/student")
    public ResponseEntity<StudentDto> saveStudent(@RequestBody StudentDto studentDto,
                                                  @PathVariable("classId") Long classId) throws Throwable {

        Student student = studentMapping.fromStudentDtoToStudent(studentDto, classId);
        Student storedStudent = studentService.saveStudent(student);
        StudentDto dto = studentMapping.fromStudentToStudentDto(storedStudent);

        return new ResponseEntity<>(
                                    dto, HttpStatus.CREATED);
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<StudentDto> getStudentById(@PathVariable("studentId") Long studentId,
                                                     @PathVariable("classId") Long classId) throws Throwable {

        Student studentById = studentService.getStudentByIdAndClassId(studentId, classId);
        StudentDto dto = studentMapping.fromStudentToStudentDto(studentById);

        return new ResponseEntity<>(
                                    dto, HttpStatus.OK);
    }

    @PutMapping("/student/{studentId}")
    public ResponseEntity<StudentDto> updateStudent(@PathVariable("studentId") Long studentId,
                                                    @PathVariable("classId") Long classId,
                                                    @RequestBody StudentDto studentDto) throws Throwable {

        Student student = studentMapping.fromStudentDtoToStudent(studentDto, classId);
        Student updatedStudent = studentService.updateStudent(studentId, student, classId);
        StudentDto dto = studentMapping.fromStudentToStudentDto(updatedStudent);

        return new ResponseEntity<>(
                                    dto, HttpStatus.OK);
    }

    @DeleteMapping("/student/{studentId}")
    public ResponseEntity<String> deleteStudent(@PathVariable("studentId") Long studentId,
                                                @PathVariable("classId") Long classId) {

        studentService.deleteStudentByIdAndClassId(studentId, classId);

        return new ResponseEntity<>(
                                    String.format(REMOVE_MESSAGE, studentId, classId), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<StudentDto>> getAllStudentsByClassId(@PathVariable("classId") Long classId) {

        List<Student> allStudents = studentService.findAllStudents(classId);
        List<StudentDto> dtoList = studentMapping.fromStudentListToStudentDtoList(allStudents);

        return new ResponseEntity<>(
                                    dtoList, HttpStatus.OK);
    }
}
