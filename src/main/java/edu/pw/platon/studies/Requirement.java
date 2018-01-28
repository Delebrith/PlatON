package edu.pw.platon.studies;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@Entity
@Data
public class Requirement {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    private Long id;

    @NotNull
    private int semesterNo;

    @NotNull
    private Integer criticalSemesterNo;

    @ManyToMany
    @JoinTable(
            name = "requirements_subjects",
            joinColumns = @JoinColumn(
                    name = "requirement_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "subject_id", referencedColumnName = "code"))
    private Collection<Subject> subjects;

    @ManyToOne
    private DegreeCourse course;

}
