package edu.pw.platon.student;

import edu.pw.platon.studies.ClassDate;
import edu.pw.platon.studies.Realisation;
import lombok.Data;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Data
public class Enrollment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String status;

    @ManyToOne
    private Realisation realisation;

    @OneToMany(mappedBy = "enrollment")
    private Collection<Mark> marks;

    @ManyToMany
    @JoinTable(
            joinColumns = @JoinColumn(name = "enrollment_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "class_date_id", referencedColumnName = "id")
    )
    private Collection<ClassDate> myClassDates;

    @ManyToOne
    private Student student;
}
