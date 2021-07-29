package by.itechart.service.studentDetails;

import by.itechart.model.StudentDetails;

public interface StudentDetailsService {

    StudentDetails saveStudentDetails(Long classId, Long studentId, StudentDetails studentDetails) throws Throwable;

    StudentDetails getStudentDetailsBySchoolClassIdAndStudentId(Long classId, Long studentId) throws Throwable;

    void deleteStudentDetailsBySchoolClassIdAndStudentId(Long classId, Long studentId);

    StudentDetails updateStudentDetails(Long classId, Long studentId, StudentDetails updateDetails) throws Throwable;
}
