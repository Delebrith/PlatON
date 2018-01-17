package edu.pw.platon.utilities;

import edu.pw.platon.student.StudentRepository;
import edu.pw.platon.user.PrivilegeRepository;
import edu.pw.platon.user.RoleRepository;
import edu.pw.platon.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class InitialDataLoader implements
        ApplicationListener<ContextRefreshedEvent> {

    private boolean alreadySetup = false;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private  RoleRepository roleRepository;
    @Autowired
    private PrivilegeRepository privilegeRepository;
    @Autowired
    private StudentRepository studentRepository;


    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (alreadySetup)
            return;
        UserDataGenerator ud = new UserDataGenerator(userRepository, roleRepository, privilegeRepository, studentRepository);
        ud.createPrivileges();
        ud.createRoles();
        ud.createUsers();

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
