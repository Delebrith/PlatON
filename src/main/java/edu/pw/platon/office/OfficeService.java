package edu.pw.platon.office;

import edu.pw.platon.student.FinancialObligationDto;

import java.util.List;
import java.util.Date;

public interface OfficeService {

    void openEnrollments();
    void closeEnrollments();
    List<FinancialObligationDto> listObligations(Integer studentBookNo);
    String addObligation(Integer studentBookNo, Float amount, String type, String accountNo, Date dueDate);
    String deleteObligation(Long id);
}
