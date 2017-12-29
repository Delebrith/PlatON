package edu.pw.platon.student;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
public class Mark {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    private Long id;

    @NotNull
    @NotEmpty
    private String type;

    private Float value;

    @ManyToOne
    Enrollment enrollment;
}
