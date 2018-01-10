package edu.pw.platon.student;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Data
public class FinancialObligation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    private Long id;

    @NotNull
    private float amount;

    @NotNull
    @NotEmpty
    private String type;

    @NotNull
    @NotEmpty
    private String accountNo;

    private Date dueDate;

    @ManyToOne
    Student student;

}
