package edu.pw.platon.studies;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@Entity
@Data
public class Subject {

    @Id
    @NotNull
    @NotEmpty
    private String code;

    @NotNull
    @NotEmpty
    private String name;

    @NotNull
    private Integer ects;

    @NotNull
    //@NotEmpty
    @ManyToOne
    private PassMethod passMethod;

    @NotNull
    @NotEmpty
    private String description;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "subject")
    private Collection<Realisation> realisations;

    @ManyToMany(mappedBy = "subjects")
    Collection<Requirement> requirements;
}
