package edu.pw.platon.user.api;

import edu.pw.platon.user.User;
import lombok.Data;

@Data
public class LoginResponse extends Response {

    private User user;
}
