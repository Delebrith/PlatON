package edu.pw.platon.user.api;

import lombok.Data;

@Data
public class LoginRequestBody extends Response {
    private String username;
    private String passwordHash;
}
