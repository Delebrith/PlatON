package edu.pw.platon.student;

import java.util.List;

public interface StudentService {

    List<FinancialObligationDto> getMyFinancialObligations(String username);
    List<Enrollment> getMyCurrentSubjects(String username);
}
