package edu.pw.platon.admin;

import edu.pw.platon.authority.AuthorityRepository;
import edu.pw.platon.office.OfficeEmployeeRepository;
import edu.pw.platon.student.StudentRepository;
import edu.pw.platon.teacher.TeacherRepository;
import edu.pw.platon.user.*;
import edu.pw.platon.utilities.UserDataGenerator;
import javafx.util.Pair;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Slf4j
@Service
public class AdminServiceImpl implements AdminService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserDataGenerator userDataGenerator;
    private final StudentRepository studentRepository;
    private final PasswordEncoder passwordEncoder;
    private final AdministratorRepository administratorRepository;
    private final AuthorityRepository authorityRepository;
    private final TeacherRepository teacherRepository;
    private final OfficeEmployeeRepository officeEmployeeRepository;

    public AdminServiceImpl(UserRepository userRepository, RoleRepository roleRepository,
                            PrivilegeRepository privilegeRepository, StudentRepository studentRepository,
                            PasswordEncoder passwordEncoder, AdministratorRepository administratorRepository,
                            AuthorityRepository authorityRepository, TeacherRepository teacherRepository,
                            OfficeEmployeeRepository officeEmployeeRepository, PasswordEncoder passwordEncoder1, AdministratorRepository administratorRepository1, AuthorityRepository authorityRepository1, TeacherRepository teacherRepository1, OfficeEmployeeRepository officeEmployeeRepository1) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder1;
        this.administratorRepository = administratorRepository1;
        this.authorityRepository = authorityRepository1;
        this.teacherRepository = teacherRepository1;
        this.officeEmployeeRepository = officeEmployeeRepository1;
        userDataGenerator = new UserDataGenerator(roleRepository, privilegeRepository, studentRepository,
                passwordEncoder, administratorRepository, authorityRepository, teacherRepository, officeEmployeeRepository);
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Role> getRoles() {
        return (List)roleRepository.findAll();
    }

    @Override
    public void resetPassword(String username, String idCardNo, String placeOfBirth) {

    }

    @Override
    public boolean addNewUser(String role, String firstName, String secondName, String lastName, String email,
                              String placeOfBirth, String idCardNo, String function, String roomNo, Integer telephoneNo,
                              Integer internalNo) {
        String username = prepareUsername(firstName, lastName);
        try {
            if (roleRepository.findByName(role) == null) return false;
            switch (role){
                case "ROLE_STUDENT":
                    userDataGenerator.createStudentIfNotFound(username, generatePassword(placeOfBirth, idCardNo), firstName,
                            secondName, lastName, email, generateStudentBookNo());
                    break;
                case "ROLE_ADMIN":
                    userDataGenerator.createAdminIfNotFound(username, generatePassword(placeOfBirth, idCardNo), firstName,
                            secondName, lastName, email, roomNo, telephoneNo);
                    break;
                case "ROLE_AUTHORITY":
                    userDataGenerator.createAuthorityIfNotFound(username, generatePassword(placeOfBirth, idCardNo), firstName,
                            secondName, lastName, email, roomNo);
                    break;
                case "ROLE_OFFICE":
                    userDataGenerator.createOfficeEmployeeIfNotFound(username, generatePassword(placeOfBirth, idCardNo), firstName,
                            secondName, lastName, email, function);
                    break;
                case "ROLE_TEACHER":
                    userDataGenerator.createTeacherIfNotFound(username, generatePassword(placeOfBirth, idCardNo), firstName,
                            secondName, lastName, email, roomNo, internalNo);
                    break;
                default:
                    return false;
            }
        } catch (Exception e){
            log.info(e.getMessage());
            return false;
        }
        return true;
    }

    @Transactional
    @Override
    public boolean modifyUser(String username, String firstName, String secondName, String lastName,
                           String email, String function, String roomNo, Integer telephoneNo, Integer internalNo) {
        //TODO implement
        return true;
    }

    @Override
    public void deleteUser(String username) {

    }

    @Override
    public List<User> getUsers(String username) {
        ArrayList<User> users = new ArrayList<>();
        if (username != null){
            User user = userRepository.findOne(username);
            if (user != null) users.add(user);
        } else {
            users = (ArrayList) userRepository.findAll();
        }
        return users;
    }

    @Override
    public List<Pair<String, Integer>> countUsersByRole() {
        return null;
    }

    private String generatePassword(String placeOfBirth, String idCardNo){
        return idCardNo.substring(0, 5) + placeOfBirth.substring(0, 2);
    }

    private Integer generateStudentBookNo(){
        if (studentRepository.findAll().equals(new ArrayList<>())) return 100000;
        Integer maxNumber = studentRepository.findTopByOrderByStudentBookNoDesc().getStudentBookNo();
        if (maxNumber != 999999) return (maxNumber + 1);
        else return 100000;
    }

    private String prepareUsername(String firstName, String lastName){
        String username = firstName.substring(0,1) + lastName;
        if (username.length() > 14) username = username.substring(0, 14);
        if (username.length() < 6){
            while (username.length() < 6) username = username.concat("0");
        }
        int counter = 2;
        while (userRepository.exists(username)){
            username = username.substring(username.length() - 1) + counter++;
        }
        return username.toLowerCase();
    }
}
