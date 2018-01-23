package edu.pw.platon.teacher;

import edu.pw.platon.studies.ClassDate;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@Entity
public class Preference {

    @NotEmpty
    @NotNull
    @Id
    Long id;

    @Min(1)
    @Max(5)
    private Integer value = 1;

    @NotNull
    @ManyToOne
    private ClassDate classDate;

    @ManyToOne
    private Form form;
}
