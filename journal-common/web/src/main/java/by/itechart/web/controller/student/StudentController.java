package by.itechart.web.controller.student;

import by.itechart.mapping.dto.student.StudentDto;
import by.itechart.mapping.dto.student.StudentDtoId;
import by.itechart.mapping.student.StudentMapper;
import by.itechart.mapping.student.StudentMapperWithSchoolClass;
import by.itechart.model.Student;
import by.itechart.service.student.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/classes/class/{classId}/students")
public class StudentController {

    private final StudentService studentService;

    private final StudentMapper studentMapper;

    private final StudentMapperWithSchoolClass customStudentMapper;

    private final String REMOVE_MESSAGE = "The student with ID = %d for a school class with ID = %d was successfully removed";


    @PostMapping("/student")
    public ResponseEntity<StudentDto> saveStudent(@RequestBody StudentDto studentDto,
                                                  @PathVariable("classId") Long classId) throws Throwable {

        Student student = customStudentMapper.studentDtoToStudent(studentDto, classId);
        Student storedStudent = studentService.saveStudent(student);
        StudentDto dto = studentMapper.studentToStudentDto(storedStudent);

        return new ResponseEntity<>(
                                    dto, HttpStatus.CREATED);
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<StudentDto> getStudentById(@PathVariable("studentId") Long studentId,
                                                     @PathVariable("classId") Long classId) throws Throwable {

        Student studentById = studentService.getStudentByIdAndClassId(studentId, classId);
        StudentDto dto = studentMapper.studentToStudentDto(studentById);

        return new ResponseEntity<>(
                                    dto, HttpStatus.OK);
    }

    @PutMapping("/student/{studentId}")
    public ResponseEntity<StudentDto> updateStudent(@PathVariable("studentId") Long studentId,
                                                    @PathVariable("classId") Long classId,
                                                    @RequestBody StudentDto studentDto) throws Throwable {

        Student student = customStudentMapper.studentDtoToStudent(studentDto, classId);
        Student updatedStudent = studentService.updateStudent(studentId, student, classId);
        StudentDto dto = studentMapper.studentToStudentDto(updatedStudent);

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
    public ResponseEntity<List<StudentDtoId>> getAllStudentsByClassId(@PathVariable("classId") Long classId) {

        List<Student> allStudents = studentService.findAllStudents(classId);
        List<StudentDtoId> dtoList = studentMapper.studentListToStudentDtoIdList(allStudents);

        return new ResponseEntity<>(
                                    dtoList, HttpStatus.OK);
    }
}
