package by.itechart.web.controller;

import by.itechart.mapping.dto.StudentDto;
import by.itechart.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/classes/class/{classId}/students")
public class StudentController {

    private StudentService studentService;

    private final String REMOVE_MESSAGE = "The student with ID = %d for a school class with ID = %d was successfully removed";

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }


    @PostMapping("/student")
    public ResponseEntity<StudentDto> saveStudent(@RequestBody StudentDto studentDto,
                                                  @PathVariable("classId") Long classId) throws Throwable {

        StudentDto storedStudentDto = studentService.saveStudent(studentDto, classId);

        return new ResponseEntity<>(
                                    storedStudentDto, HttpStatus.CREATED);
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<StudentDto> getStudentById(@PathVariable("studentId") Long studentId,
                                                     @PathVariable("classId") Long classId) throws Throwable {

        StudentDto studentById = studentService.getStudentByIdAndClassId(studentId, classId);

        return new ResponseEntity<>(
                                    studentById, HttpStatus.OK);
    }

    @PutMapping("/student/{studentId}")
    public ResponseEntity<StudentDto> updateStudent(@PathVariable("studentId") Long studentId,
                                                    @PathVariable("classId") Long classId,
                                                    @RequestBody StudentDto studentDto) throws Throwable {

        StudentDto updatedStudent = studentService.updateStudent(studentId, studentDto, classId);

        return new ResponseEntity<>(
                                    updatedStudent, HttpStatus.OK);
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

        List<StudentDto> allStudents = studentService.findAllStudents(classId);

        return new ResponseEntity<>(
                                    allStudents, HttpStatus.OK);
    }
}
