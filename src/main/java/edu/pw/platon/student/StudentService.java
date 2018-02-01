package edu.pw.platon.student;

import edu.pw.platon.studies.RealisationDto;

import java.util.List;

public interface StudentService {

    List<FinancialObligationDto> getMyFinancialObligations(String username);
    List<EnrollmentDto> getMyCurrentSubjects(String username);
    List<RealisationDto> getRealisations(String code);
    String enroll(String username, String realCode, String subjectCode, String semesterCode);
    String dismissEnroll(String username, String realCode, String subjectCode, String semesterCode);
}
