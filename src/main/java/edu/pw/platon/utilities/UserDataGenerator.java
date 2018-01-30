package edu.pw.platon.utilities;

import edu.pw.platon.admin.Administrator;
import edu.pw.platon.admin.AdministratorRepository;
import edu.pw.platon.authority.Authority;
import edu.pw.platon.authority.AuthorityRepository;
import edu.pw.platon.office.OfficeEmployee;
import edu.pw.platon.office.OfficeEmployeeRepository;
import edu.pw.platon.student.Student;
import edu.pw.platon.student.StudentRepository;
import edu.pw.platon.teacher.Teacher;
import edu.pw.platon.teacher.TeacherRepository;
import edu.pw.platon.user.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collection;

public class
UserDataGenerator {

    private final RoleRepository roleRepository;
    private final PrivilegeRepository privilegeRepository;
    private final StudentRepository studentRepository;
    private final PasswordEncoder passwordEncoder;
    private final AdministratorRepository administratorRepository;
    private final AuthorityRepository authorityRepository;
    private final TeacherRepository teacherRepository;
    private final OfficeEmployeeRepository officeEmployeeRepository;

    public UserDataGenerator(RoleRepository roleRepository,
                             PrivilegeRepository privilegeRepository, StudentRepository studentRepository,
                             PasswordEncoder passwordEncoder, AdministratorRepository administratorRepository,
                             AuthorityRepository authorityRepository, TeacherRepository teacherRepository,
                             OfficeEmployeeRepository officeEmployeeRepository) {
        this.roleRepository = roleRepository;
        this.privilegeRepository = privilegeRepository;
        this.studentRepository = studentRepository;
        this.passwordEncoder = passwordEncoder;
        this.administratorRepository = administratorRepository;
        this.authorityRepository = authorityRepository;
        this.teacherRepository = teacherRepository;
        this.officeEmployeeRepository = officeEmployeeRepository;
    }

    @Transactional
    public Privilege createPrivilegeIfNotFound(String name) {

        Privilege privilege = privilegeRepository.findByName(name);
        if (privilege == null) {
            privilege = new Privilege();
            privilege.setName(name);
            privilegeRepository.save(privilege);
        }
        return privilege;
    }

    @Transactional // ADMIN, AUTHORITY, OFFICE, STUDENT, TEACHER
    public Role createRoleIfNotFound(
            String name, String displayName, Collection<Privilege> privileges) {

            Role role = roleRepository.findByName(name);
            if (role == null) {
                role = new Role();
                role.setName(name);
                role.setDisplayName(displayName);
                role.setPrivileges(privileges);
                roleRepository.save(role);
            }
            return role;
    }

    @Transactional
    public void createPrivileges() {
        createPrivilegeIfNotFound("READ_PRIVILEGE");
        createPrivilegeIfNotFound("WRITE_PRIVILEGE");
    }

    @Transactional
    public void createRoles() {
        Privilege readPrivilege = privilegeRepository.findByName("READ_PRIVILEGE");
        Privilege writePrivilege = privilegeRepository.findByName("WRITE_PRIVILEGE");
        createRoleIfNotFound("ROLE_ADMIN", "Administrator", Arrays.asList(readPrivilege, writePrivilege));
        createRoleIfNotFound("ROLE_STUDENT", "Student", Arrays.asList(readPrivilege, writePrivilege));
        createRoleIfNotFound("ROLE_AUTHORITY", "Przedstawiciel władz", Arrays.asList(readPrivilege, writePrivilege));
        createRoleIfNotFound("ROLE_TEACHER", "Pracownik naukowy", Arrays.asList(readPrivilege, writePrivilege));
        createRoleIfNotFound("ROLE_OFFICE", "Pracownik administracyjny", Arrays.asList(readPrivilege, writePrivilege));
    }

    @Transactional
    public Student createStudentIfNotFound(String username, String password, String firstName, String secondName,
                                        String lastName, String email, Integer studentBookNo) {
        Student student = studentRepository.findOne(username);
        if (student == null) {
            student = new Student();
            student.setUsername(username);
            student.setPassword(passwordEncoder.encode(password));
            student.setEmail(email);
            student.setFirstName(firstName);
            student.setSecondName(secondName);
            student.setLastName(lastName);
            student.setStudentBookNo(studentBookNo);
            student.setRoles(Arrays.asList(roleRepository.findByName("ROLE_STUDENT")));
            studentRepository.save(student);
        }
        return student;
    }

    @Transactional
    public Administrator createAdminIfNotFound(String username, String password, String firstName, String secondName,
                                               String lastName, String email, String roomNo, Integer telephoneNo) {
        Administrator administrator = administratorRepository.findOne(username);
        if (administrator == null) {
            administrator = new Administrator();
            administrator.setUsername(username);
            administrator.setPassword(passwordEncoder.encode(password));
            administrator.setEmail(email);
            administrator.setFirstName(firstName);
            administrator.setSecondName(secondName);
            administrator.setLastName(lastName);
            administrator.setRoomNo(roomNo);
            administrator.setTelephoneNumber(telephoneNo);
            administrator.setRoles(Arrays.asList(roleRepository.findByName("ROLE_ADMIN")));
            administratorRepository.save(administrator);
        }
        return administrator;
    }

    @Transactional
    public Authority createAuthorityIfNotFound(String username, String password, String firstName, String secondName,
                                               String lastName, String email, String roomNo) {
        Authority authority = authorityRepository.findOne(username);
        if (authority == null) {
            authority = new Authority();
            authority.setUsername(username);
            authority.setPassword(passwordEncoder.encode(password));
            authority.setEmail(email);
            authority.setFirstName(firstName);
            authority.setLastName(lastName);
            authority.setRoomNo(roomNo);
            authority.setRoles(Arrays.asList(roleRepository.findByName("ROLE_AUTHORITY")));
            authorityRepository.save(authority);
        }
        return authority;
    }

    @Transactional
    public Teacher createTeacherIfNotFound(String username, String password, String firstName, String secondName,
                                           String lastName, String email, String roomNo, Integer internalNo) {
        Teacher teacher = teacherRepository.findOne(username);
        if (teacher == null) {
            teacher = new Teacher();
            teacher.setUsername(username);
            teacher.setPassword(passwordEncoder.encode(password));
            teacher.setEmail(email);
            teacher.setFirstName(firstName);
            teacher.setSecondName(secondName);
            teacher.setLastName(lastName);
            teacher.setRoomNo(roomNo);
            teacher.setInternalNo(internalNo);
            teacher.setRoles(Arrays.asList(roleRepository.findByName("ROLE_TEACHER")));
            teacherRepository.save(teacher);
        }
        return teacher;
    }

    @Transactional
    public OfficeEmployee createOfficeEmployeeIfNotFound(String username, String password, String firstName, String secondName,
                                               String lastName, String email, String function) {
        OfficeEmployee officeEmployee = officeEmployeeRepository.findOne(username);
        if (officeEmployee == null) {
            officeEmployee = new OfficeEmployee();
            officeEmployee.setUsername(username);
            officeEmployee.setPassword(passwordEncoder.encode(password));
            officeEmployee.setEmail(email);
            officeEmployee.setFirstName(firstName);
            officeEmployee.setSecondName(secondName);
            officeEmployee.setLastName(lastName);
            officeEmployee.setFunction(function);
            officeEmployee.setRoles(Arrays.asList(roleRepository.findByName("ROLE_OFFICE")));
            officeEmployeeRepository.save(officeEmployee);
        }
        return officeEmployee;
    }

    @Transactional
    public void insertUsers() {
        createAdminIfNotFound("administrator", "admin", "Adam", null,
                "Administratorski", "admin@admin.pl", "123", 123456789);
        createStudentIfNotFound("student1", "student", "Andrzej", null,
                "Studencki", "student1@student.pl", 123456);
        createStudentIfNotFound("student2", "student", "Anna", null,
                "Studencka", "student2@student.pl", 123457);
        createAuthorityIfNotFound("dziekan", "dziekan", "Dariusz", null,
                "Dziekański", "dziekan@dziekan.pl", "112");
        createOfficeEmployeeIfNotFound("office", "office", "Grażyna", "Halina",
                "Dziekanacka", "office@office.pl", "pani z dziekanatu");
        createTeacherIfNotFound("teacher1", "teacher", "Krzysztof", null,
                "Nauczycielski", "teacher1@teacher.pl", "333", 789789);
        createTeacherIfNotFound("teacher2", "teacher", "Krystyna", null,
                "Nauczycielska", "teacher2@teacher.pl", "555", 789788);
        createStudentIfNotFound("student3", "student","Jan",null,
                "Zdawalski","student3@student",123100);
        createStudentIfNotFound("student4", "student","Anotni","Edward",
                "Studencki","student4@student",123101);
        createStudentIfNotFound("student5", "student","Janina",null,
                "Mądralińska","student5@student",123102);
        createTeacherIfNotFound("teacher3", "teacher", "Jan", null,
                "Janowicz", "teacher3@teacher.pl", "333", 789790);
        createTeacherIfNotFound("teacher4", "teacher", "Józef", null,
                "Jąrdaszczak", "teacher4@teacher.pl", "321", 789791);
        createTeacherIfNotFound("teacher5", "teacher", "Anna", null,
                "Karenina", "teacher5@teacher.pl", "112", 789792);
    }
}
