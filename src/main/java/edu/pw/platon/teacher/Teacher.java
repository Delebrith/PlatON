package edu.pw.platon.teacher;

import edu.pw.platon.studies.Realisation;
import edu.pw.platon.user.User;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@Entity
@Data
@PrimaryKeyJoinColumn(name = "username")
@EqualsAndHashCode(callSuper = true)
public class Teacher extends User{

    @NotNull
    @NotEmpty
    private Integer internalNo;

    private String roomNo;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "teachers_realisations",
            joinColumns = @JoinColumn(name = "teacher_id", referencedColumnName = "username"),
            inverseJoinColumns = @JoinColumn(name = "realisation_id", referencedColumnName = "id")
    )
    private Collection<Realisation> realisations;

    public Teacher() {
        super();
    }
}
