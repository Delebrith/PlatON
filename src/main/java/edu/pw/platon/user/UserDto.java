package edu.pw.platon.user;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class UserDto {

    private String username;
    private String firstName;
    private String secondName;
    private String lastName;
    private String email;
    private Integer internalNo;
    private String roomNo;
    private Integer telephoneNo;
    private Integer studentBookNo;
    private String function;

    private List<String> roles;

}
