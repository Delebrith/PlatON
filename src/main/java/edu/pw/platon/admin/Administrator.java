package edu.pw.platon.admin;

import edu.pw.platon.user.User;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Range;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@PrimaryKeyJoinColumn(name = "username")
public class Administrator extends User {

    @Range(min = 100000000, max = 999999999)
    private Integer telephoneNumber;
    private String roomNo;

}
