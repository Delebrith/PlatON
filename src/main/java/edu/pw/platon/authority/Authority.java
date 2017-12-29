package edu.pw.platon.authority;

import edu.pw.platon.user.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@PrimaryKeyJoinColumn(name = "username")
public class Authority extends User{

    //pending requests TBD

}
