package edu.pw.platon.studies;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@Entity
@Data
public class DegreeCourse {

    @Id
    @NotNull
    @NotEmpty
    private String code;

    @NotNull
    private int ectsSum;

    @NotNull
    @NotEmpty
    private String name;

    @NotNull
    @NotEmpty
    private String specialisation;

    @NotNull
    @NotEmpty
    @Min(1)
    @Max(3)
    private int degree;

    @NotNull
    private int minEctsPerSemester;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "course")
    private Collection<Requirement> requirements;
}
