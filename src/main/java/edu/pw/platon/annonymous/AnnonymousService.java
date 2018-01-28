package edu.pw.platon.annonymous;

import edu.pw.platon.annonymous.api.*;

public interface AnnonymousService {

    Response getRequirementsInfo(String courseId);
    //TODO change parameters to make method prepare model plan for semester
    Response getScheduleInfo(String semesterCode, String subjectCode);
    Response getSubjectInfo(String subjectCode);
}
