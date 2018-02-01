package edu.pw.platon.admin;

import edu.pw.platon.user.Role;
import edu.pw.platon.user.User;
import edu.pw.platon.user.UserDto;
import javafx.util.Pair;

import java.util.List;

public interface AdminService {
    List<Role> getRoles();
    String resetPassword(String username, String idCardNo, String placeOfBirth);
    String addNewUser(String role, String firstName, String secondName, String lastName, String email, String pesel,
                       String idCardNo, String function, String roomNo, Integer telephoneNo, Integer internalNo);
    String modifyUser(String username, String firstName, String secondName, String lastName, String email,
                    String function, String roomNo, Integer telephoneNo, Integer internalNo);
    String deleteUser(String username);
    List<UserDto> getUsers(String username);
    List<Pair<String, Integer>> countUsersByRole();

}
