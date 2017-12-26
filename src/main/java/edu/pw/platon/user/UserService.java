package edu.pw.platon.user;

import org.springframework.stereotype.Service;

@Service
public interface UserService {
    String login(User user);
    void logout(User user);
    void changePassword(String oldPassword, String newPassword);
}
