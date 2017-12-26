package edu.pw.platon.user;

import lombok.Data;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
@Data
public class User {

    @Id
    @NotNull
    @NotEmpty
    @Size(min = 6, max = 12)
    private String username;

    @NotNull
    @NotEmpty
    private String passwordHash;

    @NotNull
    @NotEmpty
    private String firstName;

    @NotNull
    @NotEmpty
    private String secondName;

    @NotNull
    @NotEmpty
    private String surname;

    @NotNull
    @NotEmpty
    @Email
    private String email;

    @NotNull
    @NotEmpty
    private String role;
}
