package edu.pw.platon.utilities;

import edu.pw.platon.admin.AdministratorRepository;
import edu.pw.platon.authority.AuthorityRepository;
import edu.pw.platon.student.StudentRepository;
import edu.pw.platon.user.PrivilegeRepository;
import edu.pw.platon.user.RoleRepository;
import edu.pw.platon.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class InitialDataLoader implements
        ApplicationListener<ContextRefreshedEvent> {

    private final UserDataGenerator userDataGenerator;

    private boolean alreadySetup = false;

    public InitialDataLoader(UserRepository userRepository, RoleRepository roleRepository,
                             PrivilegeRepository privilegeRepository, StudentRepository studentRepository,
                             PasswordEncoder passwordEncoder, AdministratorRepository administratorRepository,
                             AuthorityRepository authorityRepository) {
        this.userDataGenerator = new UserDataGenerator(userRepository, roleRepository, privilegeRepository, studentRepository,
                passwordEncoder, administratorRepository, authorityRepository);
    }


    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (alreadySetup)
            return;
        userDataGenerator.createPrivileges();
        userDataGenerator.createRoles();
        userDataGenerator.createStudent();
        userDataGenerator.createAdmin();
        userDataGenerator.createAuthority();

//        List<Privilege> adminPrivileges = Arrays.asList(
//                readPrivilege, writePrivilege);
//        createRoleIfNotFound("ROLE_ADMIN", adminPrivileges);
//        createRoleIfNotFound("ROLE_USER", Arrays.asList(readPrivilege));
//
//        Role adminRole = roleRepository.findByName("ROLE_ADMIN");
//        User user = new User();
//        user.setUsername("testAdmin");
//        user.setFirstName("Test");
//        user.setLastName("Test");
//        user.setPassword("test");
//        user.setEmail("test@test.com");
//        user.setRoles(Arrays.asList(adminRole));
//        userRepository.save(user);

        alreadySetup = true;
    }
}
