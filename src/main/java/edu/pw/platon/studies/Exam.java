package edu.pw.platon.studies;

import lombok.Data;

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
    private Float durationTime;

    @OneToOne
    Realisation realisation;
}
