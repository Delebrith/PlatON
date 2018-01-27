package edu.pw.platon.student;

import edu.pw.platon.user.User;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Range;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@PrimaryKeyJoinColumn(name = "username")
public class Student extends User {

    @NotNull
    @Range(min = 100000, max = 999999)
    private Integer studentBookNo;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "student")
    private Collection<FinancialObligation> financialObligations;

    @OneToMany(mappedBy = "student")
    private Collection<Enrollment> enrollments;
}