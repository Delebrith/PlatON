package edu.pw.platon.authority;

import edu.pw.platon.user.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@PrimaryKeyJoinColumn(name = "username")
public class Authority extends User{

    private String roomNo;

    public Authority() {
        super();
    }
}
