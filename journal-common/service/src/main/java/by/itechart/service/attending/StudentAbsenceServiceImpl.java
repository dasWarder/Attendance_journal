package by.itechart.service.attending;

import by.itechart.model.Absence;
import by.itechart.model.Student;
import by.itechart.service.absence.AbsenceService;
import by.itechart.service.student.StudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static by.itechart.model.util.ValidationUtil.validateParams;

@Slf4j
@Service
@RequiredArgsConstructor
public class StudentAbsenceServiceImpl implements StudentAbsenceService {

    private final StudentService studentService;

    private final AbsenceService absenceService;


    //THIS IMPLEMENTATION OR BETTER USE @QUERY AND PICK UP RIGHT FROM DB???
    @Override
    public List<Student> getAllByAbsenceDatesAndSchoolClassId(LocalDate absenceDate, Long classId) throws Throwable {

        validateParams(absenceDate, classId);

        log.info("Receive a collection of absence students for a class with ID = {} on the date = {}",
                                                                                                      classId, absenceDate);
        Absence absenceByAbsenceDate = absenceService.getAbsenceByAbsenceDate(absenceDate);
        Set<Student> absenceDateStudents = absenceByAbsenceDate.getStudents();
        List<Student> allStudentsByClassId = studentService.findAllStudents(classId);

        List<Student> studentsByAbsenceDate = filteringStudentByAbsenceDate(
                                                                            allStudentsByClassId,
                                                                            absenceDateStudents);
        return studentsByAbsenceDate;
    }

    @Override
    public Student addStudentToAbsenceList(Long classId, Long studentId, LocalDate absenceDate) throws Throwable {

        validateParams(classId, studentId, absenceDate);

        log.info("Add student to the absence list");

        Absence absenceByAbsenceDate = checkAbsenceObjectAndReturnOldOrCreateNew(absenceDate);
        Student studentByIdAndClassId = studentService.getStudentByIdAndClassId(studentId, classId);

        studentByIdAndClassId.getAbsenceDates()
                                                .add(absenceByAbsenceDate);
        Student storedStudent = studentService.saveStudent(studentByIdAndClassId);

        return storedStudent;
    }

    @Override
    public List<Student> addStudentsToAbsenceList(List<Student> students, LocalDate absenceDate) {

        validateParams(students, absenceDate);

        log.info("Store a list of users to the absence list");

        Absence absence = checkAbsenceObjectAndReturnOldOrCreateNew(absenceDate);

        students.stream().forEach(s -> s.getAbsenceDates()
                                                          .add(absence));
        List<Student> storedStudents = studentService.saveAllStudents(students);

        return storedStudents;
    }

    @Override
    public void deleteStudentFromAbsenceList(Long classId, Long studentId, LocalDate absenceDate) throws Throwable {

        validateParams(classId, studentId, absenceDate);

        log.info("Delete student from the absence list");

        Absence absenceByAbsenceDate = absenceService.getAbsenceByAbsenceDate(absenceDate);
        Student studentByIdAndClassId = studentService.getStudentByIdAndClassId(studentId, classId);
        List<Absence> studentsAbsenceDates = studentByIdAndClassId.getAbsenceDates();

        studentsAbsenceDates.stream()
                            .filter(absence -> studentsAbsenceDates.contains(absenceByAbsenceDate))
                            .collect(Collectors.toList())
                                    .stream()
                                    .forEach(absence -> studentsAbsenceDates.remove(absenceByAbsenceDate));

        studentService.saveStudent(studentByIdAndClassId);
    }



    private List<Student> filteringStudentByAbsenceDate(List<Student> allStudentsByClassId,
                                                        Set<Student> absenceDateStudents) {

        List<Student> studentsByAbsenceDate = allStudentsByClassId.stream()
                                                                        .filter(absenceDateStudents::contains)
                                                                        .collect(Collectors.toList());
        return studentsByAbsenceDate;
    }

    private Absence checkAbsenceObjectAndReturnOldOrCreateNew(LocalDate absenceDate) {

        Absence absence = null;

        try {

            absence = absenceService.getAbsenceByAbsenceDate(absenceDate);

        } catch (Throwable throwable) {

            Absence newAbsence = Absence.builder()
                                                .absenceDate(absenceDate)
                                                .students(new HashSet<>())
                                                .build();
            absence = absenceService.createAbsence(newAbsence);
        }

        return absence;
    }

}
