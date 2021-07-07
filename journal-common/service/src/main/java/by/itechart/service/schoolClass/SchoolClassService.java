package by.itechart.service.schoolClass;

import by.itechart.model.SchoolClass;

import java.util.List;

public interface SchoolClassService {

    SchoolClass saveSchoolClass(SchoolClass schoolClass);

    SchoolClass getSchoolClassById(Long classId) throws Throwable;

    SchoolClass getSchoolClassByName(String name) throws Throwable;

    SchoolClass updateSchoolClass(Long classId, SchoolClass updateSchoolClass) throws Throwable;

    void deleteSchoolClassById(Long classId);

    void deleteSchoolClassByName(String name);

    List<SchoolClass> getAllSchoolClasses();

}
