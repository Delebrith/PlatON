package edu.pw.platon.admin;

import edu.pw.platon.user.Role;
import edu.pw.platon.user.User;
import javafx.util.Pair;

import java.util.List;

public interface AdminService {
    List<Role> getRoles();
    void resetPassword(String username, String idCardNo, String placeOfBirth);
    boolean addNewUser(String role, String firstName, String secondName, String lastName, String email, String pesel,
                       String idCardNo, String function, String roomNo, Integer telephoneNo, Integer internalNo);
    boolean modifyUser(String username, String firstName, String secondName, String lastName, String email,
                    String function, String roomNo, Integer telephoneNo, Integer internalNo);
    void deleteUser(String username);
    List<User> getUsers(String username);
    List<Pair<String, Integer>> countUsersByRole();

}
