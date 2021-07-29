package by.itechart.service.studentDetails;

import by.itechart.model.Student;
import by.itechart.model.StudentDetails;
import by.itechart.model.util.ValidationUtil;
import by.itechart.repository.StudentDetailsRepository;
import by.itechart.service.student.StudentService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static by.itechart.model.util.ValidationUtil.validateOptional;
import static by.itechart.model.util.ValidationUtil.validateParams;

@Slf4j
@Service
@RequiredArgsConstructor
public class StudentDetailsServiceImpl implements StudentDetailsService {

    private final StudentDetailsRepository detailsRepository;

    private final StudentService studentService;

    @Override
    public StudentDetails saveStudentDetails(Long classId,  Long studentId, StudentDetails studentDetails) throws Throwable {

        validateParams(classId, studentId, studentDetails);

        log.info("Store a new student details");

        Student studentByIdAndClassId = studentService.getStudentByIdAndClassId(studentId, classId);

        studentDetails.setStudent(studentByIdAndClassId);
        StudentDetails storedDetails = detailsRepository.save(studentDetails);

        return storedDetails;
    }

    @Override
    public StudentDetails getStudentDetailsBySchoolClassIdAndStudentId(Long classId, Long studentId) throws Throwable {

        validateParams(classId, studentId);

        log.info("Receive student details by class ID = {} and student ID = {}",
                                                                                classId, studentId);
        Optional<StudentDetails> possibleDetails = detailsRepository
                                                    .getStudentDetailsByStudentSchoolClassIdAndStudentId(classId, studentId);
        StudentDetails validDetails = validateOptional(possibleDetails, StudentDetails.class);

        return validDetails;
    }

    @Override
    @Transactional
    public void deleteStudentDetailsBySchoolClassIdAndStudentId(Long classId, Long studentId) {

        validateParams(classId, studentId);

        log.info("Remove student details with class ID = {} and student ID = {}",
                                                                                classId, studentId);
        detailsRepository.deleteStudentDetailsByStudentSchoolClassIdAndStudentId(classId, studentId);
    }

    @Override
    public StudentDetails updateStudentDetails(Long classId, Long studentId, StudentDetails updateDetails) throws Throwable {

        validateParams(classId, studentId, updateDetails);

        log.info("Update student details for class ID = {} and student ID = {}",
                                                                                classId, studentId);

        StudentDetails validDetails = getStudentDetailsBySchoolClassIdAndStudentId(classId, studentId);

        updateDetails.setId(validDetails.getId());
        updateDetails.setStudent(validDetails.getStudent());

        StudentDetails updatedDetails = detailsRepository.save(updateDetails);

        return updatedDetails;
    }
}
