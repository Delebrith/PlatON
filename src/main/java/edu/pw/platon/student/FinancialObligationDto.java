package edu.pw.platon.student;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class FinancialObligationDto {

    private Long id;
    private float amount;
    private String type;
    private String accountNo;
    private Date dueDate;
    private String studentName;
    private Integer studentBookNo;
}
