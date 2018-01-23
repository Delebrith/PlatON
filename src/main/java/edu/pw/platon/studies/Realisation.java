package edu.pw.platon.studies;

import edu.pw.platon.teacher.Teacher;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@Entity
@Data
public class Realisation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @NotEmpty
    private String semesterCode;

    @NotBlank
    private String name;

    @NotNull
    @ManyToOne
    Subject subject;

    @ManyToMany(mappedBy = "realisations")
    @NotNull
    Collection<Teacher> teachers;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "realisation")
    Collection<Material> materials;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "realisation")
    Collection<ClassDate> classDates;

    @OneToOne(cascade = CascadeType.ALL)
    Exam exam;
}
