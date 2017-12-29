package edu.pw.platon.admin;

import edu.pw.platon.user.User;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@PrimaryKeyJoinColumn(name = "username")
public class Administrator extends User {

    @NotNull
    @NotEmpty
    private int telephoneNumber;
    private String roomNo;
}
