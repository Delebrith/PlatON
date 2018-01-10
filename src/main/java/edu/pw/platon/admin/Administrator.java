package edu.pw.platon.admin;

import edu.pw.platon.user.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@PrimaryKeyJoinColumn(name = "username")
public class Administrator extends User {

    private int telephoneNumber;
    private String roomNo;
}
