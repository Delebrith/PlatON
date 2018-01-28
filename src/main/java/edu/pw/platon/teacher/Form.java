package edu.pw.platon.teacher;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@Entity
@Data
public class Form {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    private Long id;

    @OneToMany(mappedBy = "form")
    Collection<Preference> preferences;

    @NotNull
    @NotEmpty
    private String name;

    private boolean manyChoices;

    @NotNull
    @NotEmpty
    private String status;
}
