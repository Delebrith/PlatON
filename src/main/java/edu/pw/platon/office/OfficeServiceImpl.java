package edu.pw.platon.office;

import edu.pw.platon.student.*;
import edu.pw.platon.utilities.CustomProperties;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OfficeServiceImpl implements OfficeService {

    public static final String SUCCESS_MESSAGE = "Sukces! ";

    private CustomProperties customProperties;
    private final FinancialObligationRepository financialObligationRepository;
    private final StudentRepository studentRepository;

    public OfficeServiceImpl(CustomProperties customProperties, FinancialObligationRepository financialObligationRepository, StudentRepository studentRepository) {
        this.customProperties = customProperties;
        this.financialObligationRepository = financialObligationRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public void openEnrollments() {
        customProperties.setEnrollments("open");
    }

    @Override
    public void closeEnrollments() {
        customProperties.setEnrollments("closed");
    }

    @Override
    public List<FinancialObligationDto> listObligations(Integer studentBookNo) {
        List<FinancialObligation> financialObligations;
        if (studentBookNo != null) {
            financialObligations = financialObligationRepository
                    .findByStudent(studentRepository.findFirstByStudentBookNo(studentBookNo));
        } else {
            financialObligations = (List) financialObligationRepository.findAll();
        }
        List<FinancialObligationDto> finantialObligationDtos = new ArrayList<>();
        for (FinancialObligation financialObligation : financialObligations) {
            String name = financialObligation.getStudent().getFirstName();
            if (financialObligation.getStudent().getSecondName() != null) name = name + " " + financialObligation.getStudent().getSecondName();
            name = name + " " + financialObligation.getStudent().getLastName();
            finantialObligationDtos.add(FinancialObligationDto.builder()
                    .id(financialObligation.getId())
                    .studentBookNo(financialObligation.getStudent().getStudentBookNo())
                    .accountNo(financialObligation.getAccountNo())
                    .amount(financialObligation.getAmount())
                    .dueDate(financialObligation.getDueDate())
                    .type(financialObligation.getType())
                    .studentName(name)
                    .build());
        }
        return finantialObligationDtos;
    }

    @Override
    @Transactional
    public String addObligation(Integer studentBookNo, Float amount, String type, String accountNo, Date dueDate) {
        Student student = studentRepository.findFirstByStudentBookNo(studentBookNo);
        if (student == null) return "Nie znaleziono studenta o podanym numerze indeksu.";
        FinancialObligation financialObligation = new FinancialObligation();
        financialObligation.setAccountNo(accountNo);
        financialObligation.setAmount(amount);
        financialObligation.setDueDate(dueDate);
        financialObligation.setType(type);
        financialObligation.setStudent(student);
        financialObligationRepository.save(financialObligation);
//        student.getFinancialObligations().add(financialObligation);
//        studentRepository.save(student);
        return SUCCESS_MESSAGE + "Dodano nowe zobowiązanie finansowe dla studenta " + student.getStudentBookNo();
    }

    @Override
    public String deleteObligation(Long id) {
        FinancialObligation financialObligation = financialObligationRepository.findOne(id);
        if (financialObligation == null) {
            return "Brak rekordu w bazie";
        } else {
            financialObligationRepository.delete(id);
            return SUCCESS_MESSAGE + "Usunięto rekord z bazy";
        }
    }
}
