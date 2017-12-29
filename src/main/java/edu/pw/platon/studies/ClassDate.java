package edu.pw.platon.studies;

import edu.pw.platon.student.Enrollment;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@Entity
@Data
public class ClassDate {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    private Long id;

    @NotNull
    @NotEmpty
    private String dayOfTheWeek;

    @NotEmpty
    @NotNull
    private String hour;

    private String room;

    @ManyToMany
    @JoinTable(name = "classes_types",
        joinColumns = @JoinColumn(name = "class_date_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "type_id", referencedColumnName = "id"))
    private Collection<ClassType> classTypes;

    @ManyToOne
    private Realisation realisation;

    @ManyToMany(mappedBy = "myClassDates")
    private Collection<Enrollment> enrollments;
}
