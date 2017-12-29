package edu.pw.platon.student;

import edu.pw.platon.studies.ClassDate;
import edu.pw.platon.teacher.Form;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Data
public class Preference {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    private Long id;

    @NotNull
    @ManyToOne
    ClassDate classDatePreference;

    @Min(0)
    @Max(5)
    private Integer mark;

    @NotNull
    @ManyToOne
    Enrollment enrollment;

    @NotNull
    @ManyToOne
    ClassDate classDateChoice;

    @NotNull
    @ManyToOne
    Form form;
}
