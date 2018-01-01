package edu.pw.platon.user.api;

import lombok.Data;

@Data
public class ChangePasswordRequestBody {

    private String username;
    private String newPasswordHash;
    private String oldPasswordHash;
}
