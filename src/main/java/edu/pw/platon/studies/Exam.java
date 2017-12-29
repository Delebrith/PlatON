package edu.pw.platon.studies;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Data
public class Exam {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    private Long id;

    @NotNull
    private Date date;

    @NotNull
    @NotEmpty
    private String startTime;

    @NotNull
    @NotEmpty
    private String endTime;

    @OneToOne
    Realisation realisation;
}
