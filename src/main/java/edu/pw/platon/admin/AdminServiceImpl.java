package edu.pw.platon.admin;

import edu.pw.platon.authority.Authority;
import edu.pw.platon.authority.AuthorityRepository;
import edu.pw.platon.office.OfficeEmployee;
import edu.pw.platon.office.OfficeEmployeeRepository;
import edu.pw.platon.student.Student;
import edu.pw.platon.student.StudentRepository;
import edu.pw.platon.teacher.Teacher;
import edu.pw.platon.teacher.TeacherRepository;
import edu.pw.platon.user.*;
import edu.pw.platon.utilities.UserDataGenerator;
import javafx.util.Pair;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
public class AdminServiceImpl implements AdminService {

    public static final String SUCCESS_MESSAGE = "Sukces! ";

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserDataGenerator userDataGenerator;
    private final StudentRepository studentRepository;
    private final AdministratorRepository administratorRepository;
    private final AuthorityRepository authorityRepository;
    private final TeacherRepository teacherRepository;
    private final OfficeEmployeeRepository officeEmployeeRepository;
    private final PasswordEncoder passwordEncoder;

    public AdminServiceImpl(UserRepository userRepository, RoleRepository roleRepository,
                            PrivilegeRepository privilegeRepository, StudentRepository studentRepository,
                            PasswordEncoder passwordEncoder, AdministratorRepository administratorRepository,
                            AuthorityRepository authorityRepository, TeacherRepository teacherRepository,
                            OfficeEmployeeRepository officeEmployeeRepository, AdministratorRepository administratorRepository1, AuthorityRepository authorityRepository1, TeacherRepository teacherRepository1, OfficeEmployeeRepository officeEmployeeRepository1) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.administratorRepository = administratorRepository1;
        this.authorityRepository = authorityRepository1;
        this.teacherRepository = teacherRepository1;
        this.officeEmployeeRepository = officeEmployeeRepository1;
        userDataGenerator = new UserDataGenerator(roleRepository, privilegeRepository, studentRepository,
                passwordEncoder, administratorRepository, authorityRepository, teacherRepository, officeEmployeeRepository);
        this.studentRepository = studentRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<Role> getRoles() {
        return (List)roleRepository.findAll();
    }

    @Transactional
    @Override
    public String resetPassword(String username, String idCardNo, String placeOfBirth) {
        if (!userRepository.exists(username)) return "Nie znaleziono użytkownika.";
        User user = userRepository.findOne(username);
        user.setPassword(passwordEncoder.encode(generatePassword(placeOfBirth, idCardNo)));
        userRepository.save(user);
        return SUCCESS_MESSAGE + "Zresetowano hasło. Nowe hasło: 5 pierwszych znaków numeru dokumentu tożsamości +" +
                " 2 pierwsze znaki nazwy miasta urodzenia.";
    }

    @Transactional
    @Override
    public String addNewUser(String role, String firstName, String secondName, String lastName, String email,
                              String placeOfBirth, String idCardNo, String function, String roomNo, Integer telephoneNo,
                              Integer internalNo) {
        String username = prepareUsername(firstName, lastName);
        try {
            if (roleRepository.findByName(role) == null) return "Nie znaleziono podanej roli.";
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
                    return "Nie znaleziono podanej roli";
            }
        } catch (Exception e){
            log.info(e.getMessage());
            return "Dodawanie do bazy nie powiodło się";
        }
        return SUCCESS_MESSAGE + "Pomyślnie dodano użytkownika!";
    }

    @Transactional
    @Override
    public String modifyUser(String username, String firstName, String secondName, String lastName,
                           String email, String function, String roomNo, Integer telephoneNo, Integer internalNo) {
        if (!userRepository.exists(username)) return "Nie znaleziono użytkownika";
        User user = userRepository.findOne(username);
        if (firstName != null && !firstName.isEmpty()) user.setFirstName(firstName);
        if (secondName != null) user.setSecondName(secondName);
        if (lastName != null && !lastName.isEmpty()) user.setLastName(lastName);
        if (email != null && !email.isEmpty() && email.matches("[\\w]+[@][\\w]+[.][\\w]+")) user.setEmail(email);
        userRepository.save(user);

        Role role = user.getRoles().iterator().next();
        switch (role.getName()){
            case "ROLE_ADMIN":
                Administrator administrator = administratorRepository.findOne(username);
                if (roomNo != null) administrator.setRoomNo(roomNo);
                if (telephoneNo != null && telephoneNo < 999999999) administrator.setTelephoneNumber(telephoneNo);
                administratorRepository.save(administrator);
                break;
            case "ROLE_AUTHORITY":
                Authority authority = authorityRepository.findOne(username);
                if (roomNo != null) authority.setRoomNo(roomNo);
                authorityRepository.save(authority);
                break;
            case "ROLE_OFFICE":
                OfficeEmployee officeEmployee = officeEmployeeRepository.findOne(username);
                if (function != null && !function.isEmpty()) officeEmployee.setFunction(function);
                officeEmployeeRepository.save(officeEmployee);
                break;
            case "ROLE_TEACHER":
                Teacher teacher = teacherRepository.findOne(username);
                if (internalNo != null) teacher.setInternalNo(internalNo);
                if (roomNo != null) teacher.setRoomNo(roomNo);
                teacherRepository.save(teacher);
                break;
            default:
        }
        return SUCCESS_MESSAGE + "Pomyślnie wprowadzono zmiany!";
    }

    @Transactional
    @Override
    public String deleteUser(String username) {
        if (!userRepository.exists(username)) return "Nie znaleziono użytkownika.";
        userRepository.delete(username);
        return  SUCCESS_MESSAGE + "Usunięto konto.";
    }

    @Override
    public List<UserDto> getUsers(String username) {
        ArrayList<UserDto> userDtos = new ArrayList<>();
        if (username != null){
            User user = userRepository.findOne(username);
            if (user != null) {
                UserDto userDto = prepareUserDto(user);
                userDtos.add(userDto);
            }
        } else {
            List<User> users = (ArrayList) userRepository.findAll();
            for (User user:
                 users) {
                UserDto userDto = prepareUserDto(user);
                userDtos.add(userDto);
            }
        }
        return userDtos;
    }

    private UserDto prepareUserDto(User user){
        UserDto userDto = UserDto.builder()
                .username(user.getUsername())
                .firstName(user.getFirstName())
                .secondName(user.getSecondName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .build();
        List<String> roles = new ArrayList<>();
        for (Role role :
                user.getRoles()) {
            roles.add(role.getDisplayName());
            switch (role.getName()){
                case "ROLE_STUDENT":
                    Student student = studentRepository.findOne(user.getUsername());
                    userDto.setStudentBookNo(student.getStudentBookNo());
                    break;
                case "ROLE_ADMIN":
                    Administrator administrator = administratorRepository.findOne(user.getUsername());
                    userDto.setRoomNo(administrator.getRoomNo());
                    userDto.setTelephoneNo(administrator.getTelephoneNumber());
                    break;
                case "ROLE_AUTHORITY":
                    Authority authority = authorityRepository.findOne(user.getUsername());
                    userDto.setRoomNo(authority.getRoomNo());
                    break;
                case "ROLE_OFFICE":
                    OfficeEmployee officeEmployee = officeEmployeeRepository.findOne(user.getUsername());
                    userDto.setFunction(officeEmployee.getFunction());
                    break;
                case "ROLE_TEACHER":
                    Teacher teacher = teacherRepository.findOne(user.getUsername());
                    userDto.setInternalNo(teacher.getInternalNo());
                    userDto.setRoomNo(teacher.getRoomNo());
                    break;
                default:
            }
        }
        userDto.setRoles(roles);
        return userDto;
    }

    @Override
    public List<Pair<String, Integer>> countUsersByRole() {
        List<Pair<String, Integer>> usersRoles = new ArrayList<>();
        Iterable<Role> roles = roleRepository.findAll();
        for (Role role:
             roles) {
            usersRoles.add(new Pair<>(role.getDisplayName(), userRepository.countAllByRoles(Arrays.asList(role))));
        }
        return usersRoles;
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
