package by.itechart.service.attending;

import by.itechart.model.Absence;
import by.itechart.model.Student;
import by.itechart.service.absence.AbsenceService;
import by.itechart.service.student.StudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static by.itechart.model.util.ValidationUtil.validateParams;

/**
 * A service for working with a list of absence students. Possible operations: storing, removing and receiving.\n
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class StudentAbsenceServiceImpl implements StudentAbsenceService {

    private final StudentService studentService;

    private final AbsenceService absenceService;


    //THIS IMPLEMENTATION OR BETTER USE @QUERY AND PICK UP RIGHT FROM DB???

    /**
     * Receive a list of students, that not presented at @param absenceDate date./n
     * @param absenceDate a day, which one the collection of students must be found
     * @param classId to which one the collection of students belong
     * @return The list of student
     * @throws Throwable AbsenceNotFoundException if there is no absence students on this date
     */
    @Override
    public List<Student> getAllByAbsenceDatesAndSchoolClassId(LocalDate absenceDate, Long classId, Pageable pageable)
                                                                                                    throws Throwable {

        validateParams(absenceDate, classId, pageable);

        log.info("Receive a collection of absence students for a class with ID = {} on the date = {}",
                                                                                                      classId, absenceDate);
        Absence absenceByAbsenceDate = absenceService.getAbsenceByAbsenceDate(absenceDate);
        Set<Student> absenceDateStudents = absenceByAbsenceDate.getStudents();
        List<Student> allStudentsByClassId = studentService.findAllStudents(classId, pageable);

        List<Student> studentsByAbsenceDate = filteringStudentByAbsenceDate(
                                                                            allStudentsByClassId,
                                                                            absenceDateStudents);
        return studentsByAbsenceDate;
    }

    /**
     * Include students into absence list by a date. If the date is absence, a new absence object will be created
     * @param classId of a class which one the students must belong
     * @param studentId the id of a student, that must be added into absence list
     * @param absenceDate a date of absence for a student
     * @return a student, that was stored into the database
     * @throws Throwable StudentNotFoundException if the student with the ID doesn't exist
     */
    @Override
    @Transactional
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

    /**
     * Adding a collection of students into the absence list
     * @param students a list of absence students
     * @param absenceDate a date of the absence for the students
     * @return the collection of stored into the absence list students
     */
    @Override
    @Transactional
    public List<Student> addStudentsToAbsenceList(List<Student> students, LocalDate absenceDate) {

        validateParams(students, absenceDate);

        log.info("Store a list of users to the absence list");

        Absence absence = checkAbsenceObjectAndReturnOldOrCreateNew(absenceDate);

        students.stream().forEach(s -> s.getAbsenceDates()
                                                          .add(absence));
        List<Student> storedStudents = studentService.saveAllStudents(students);

        return storedStudents;
    }

    /**
     * Removing a student from the absence list
     * @param classId an ID af a class, which one the student must belong
     * @param studentId the ID of the student, which must be removed
     * @param absenceDate the date, from which absence list the student must be removed
     * @throws Throwable AbsenceNotFoundException || StudentNotFoundException\n
     * if the absence object or the student with ID doesn't exist
     */
    @Override
    @Transactional
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
