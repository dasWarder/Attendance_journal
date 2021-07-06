package by.itechart.web.controller;

import by.itechart.mapping.dto.StudentDto;
import by.itechart.mapping.dto.StudentDtoId;
import by.itechart.mapping.student.StudentMapper;
import by.itechart.model.SchoolClass;
import by.itechart.model.Student;
import by.itechart.repository.SchoolClassRepository;
import by.itechart.service.schoolClass.SchoolClassService;
import by.itechart.service.student.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequiredArgsConstructor
@RequestMapping("/classes/class/{classId}/students")
public class StudentController {

    private final StudentService studentService;

    private final StudentMapper studentMapper;

    private final SchoolClassService classService;

    private final String REMOVE_MESSAGE = "The student with ID = %d for a school class with ID = %d was successfully removed";


    @PostMapping("/student")
    public ResponseEntity<StudentDto> saveStudent(@RequestBody StudentDto studentDto,
                                                  @PathVariable("classId") Long classId) throws Throwable {

        Student student = studentMapper.studentDtoToStudent(studentDto);
        setSchoolClassOrThrowsException(student, classId);
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

        Student student = studentMapper.studentDtoToStudent(studentDto);
        setSchoolClassOrThrowsException(student, classId);
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
        List<StudentDtoId> dtoList = studentMapper.map(allStudents);

        return new ResponseEntity<>(
                                    dtoList, HttpStatus.OK);
    }


    private void setSchoolClassOrThrowsException(Student student, Long classId) throws Throwable {

        SchoolClass validSchoolClass = classService.getSchoolClassById(classId);

        student.setSchoolClass(validSchoolClass);
    }
}
