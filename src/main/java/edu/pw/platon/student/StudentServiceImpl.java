package edu.pw.platon.student;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<FinancialObligationDto> getMyFinancialObligations(String username) {
        Student student = studentRepository.findOne(username);
        if (student == null) return null; // should never happen
        Collection<FinancialObligation> financialObligations = student.getFinancialObligations();
        List<FinancialObligationDto> financialObligationDtos = new ArrayList<>();
        for (FinancialObligation financialObligation:
             financialObligations) {
            String name = student.getFirstName();
            if (student.getSecondName() != null) name = name + " " + student.getSecondName();
            name = name + " " + student.getLastName();
            financialObligationDtos.add(FinancialObligationDto.builder()
                    .id(financialObligation.getId())
                    .studentName(name)
                    .type(financialObligation.getType())
                    .amount(financialObligation.getAmount())
                    .dueDate(financialObligation.getDueDate())
                    .accountNo(financialObligation.getAccountNo())
                    .studentBookNo(student.getStudentBookNo())
                    .build());
        }
        return financialObligationDtos;
    }

    @Override
    public List<Enrollment> getMyCurrentSubjects(String username) {
        return null;
    }
}
