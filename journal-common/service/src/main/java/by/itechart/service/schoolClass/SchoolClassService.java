package by.itechart.service.schoolClass;

import by.itechart.model.SchoolClass;

import java.util.List;

public interface SchoolClassService {

    SchoolClass saveSchoolClass(SchoolClass schoolClass);

    SchoolClass getSchoolClassById(Long classId, String username) throws Throwable;

    SchoolClass getSchoolClassByName(String name, String username) throws Throwable;

    SchoolClass updateSchoolClass(Long classId, SchoolClass updateSchoolClass, String username) throws Throwable;

    void deleteSchoolClassById(Long classId, String username);

    void deleteSchoolClassByName(String name, String username);

    List<SchoolClass> getAllSchoolClasses(String username);

}
