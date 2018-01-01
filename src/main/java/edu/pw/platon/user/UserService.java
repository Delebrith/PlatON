package edu.pw.platon.user;

import edu.pw.platon.user.api.*;

public interface UserService {
    Response login(String username, String passwordhash);
    void logout(User user);
    Response changePassword(String username, String oldPasswordHash, String newPasswordHash);
    Response getRequirementsInfo(String courseId);
    Response getScheduleInfo(String semesterCode, String subjectCode);
    Response getSubjectInfo(String subjectCode);
}
