package edu.pw.platon.utilities;

import edu.pw.platon.admin.Administrator;
import edu.pw.platon.admin.AdministratorRepository;
import edu.pw.platon.authority.Authority;
import edu.pw.platon.authority.AuthorityRepository;
import edu.pw.platon.student.Student;
import edu.pw.platon.student.StudentRepository;
import edu.pw.platon.user.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collection;

public class UserDataGenerator {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PrivilegeRepository privilegeRepository;
    private final StudentRepository studentRepository;
    private final PasswordEncoder passwordEncoder;
    private final AdministratorRepository administratorRepository;
    private final AuthorityRepository authorityRepository;

    public UserDataGenerator(UserRepository userRepository, RoleRepository roleRepository,
                             PrivilegeRepository privilegeRepository, StudentRepository studentRepository,
                             PasswordEncoder passwordEncoder, AdministratorRepository administratorRepository,
                             AuthorityRepository authorityRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.privilegeRepository = privilegeRepository;
        this.studentRepository = studentRepository;
        this.passwordEncoder = passwordEncoder;
        this.administratorRepository = administratorRepository;
        this.authorityRepository = authorityRepository;
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
            String name, Collection<Privilege> privileges) {

        Role role = roleRepository.findByName(name);
        if (role == null) {
            role = new Role();
            role.setName(name);
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
        createRoleIfNotFound("ROLE_ADMIN", Arrays.asList(readPrivilege, writePrivilege));
        createRoleIfNotFound("ROLE_STUDENT", Arrays.asList(readPrivilege, writePrivilege));
        createRoleIfNotFound("ROLE_AUTHORITY", Arrays.asList(readPrivilege, writePrivilege));
        createRoleIfNotFound("ROLE_TEACHER", Arrays.asList(readPrivilege, writePrivilege));
        createRoleIfNotFound("ROLE_OFFICE", Arrays.asList(readPrivilege, writePrivilege));
    }

    @Transactional
    public void createStudent() {
        Student student = new Student();
        student.setUsername("student");
        student.setPassword(passwordEncoder.encode("student"));
        student.setEmail("student@student.pl");
        student.setFirstName("Jan");
        student.setLastName("Studencki");
        student.setStudentBookNo(123456);
        student.setRoles(Arrays.asList(roleRepository.findByName("ROLE_STUDENT")));
        studentRepository.save(student);
    }

    @Transactional
    public void createAdmin() {
        Administrator administrator = new Administrator();
        administrator.setUsername("administrator");
        administrator.setPassword(passwordEncoder.encode("admin"));
        administrator.setEmail("admin@admin.pl");
        administrator.setFirstName("Adam");
        administrator.setLastName("Administratorski");
        administrator.setRoomNo("09");
        administrator.setTelephoneNumber(123456789);
        administrator.setRoles(Arrays.asList(roleRepository.findByName("ROLE_ADMIN")));
        administratorRepository.save(administrator);
    }

    @Transactional
    public void createAuthority() {
        Authority authority = new Authority();
        authority.setUsername("dziekan");
        authority.setPassword(passwordEncoder.encode("dziekan"));
        authority.setEmail("dziekan@dziekan.pl");
        authority.setFirstName("Dariusz");
        authority.setLastName("Dziekański");
        authority.setRoomNo("112");
        authority.setRoles(Arrays.asList(roleRepository.findByName("ROLE_AUTHORITY")));
        authorityRepository.save(authority);
    }


}
