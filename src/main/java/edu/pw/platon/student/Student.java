package edu.pw.platon.student;

import edu.pw.platon.user.User;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@PrimaryKeyJoinColumn(name = "username")
public class Student extends User {

    @NotNull
    @NotEmpty
    private int studentBookNo;

    @OneToMany(cascade = CascadeType.ALL)
    Collection<FinancialObligation> financialObligations;
}