package edu.pw.platon.utilities;

import edu.pw.platon.admin.AdministratorRepository;
import edu.pw.platon.authority.AuthorityRepository;
import edu.pw.platon.student.StudentRepository;
import edu.pw.platon.studies.PassMethodRepository;
import edu.pw.platon.studies.RealisationRepository;
import edu.pw.platon.studies.SubjectRepository;
import edu.pw.platon.teacher.TeacherRepository;
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

    private boolean alreadySetup = false;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private  RoleRepository roleRepository;
    @Autowired
    private PrivilegeRepository privilegeRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private SubjectRepository subjectRepository;
    @Autowired
    private RealisationRepository realisationRepository;
    @Autowired
    private PassMethodRepository passMethodRepository;
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AdministratorRepository administratorRepository;
    @Autowired
    private AuthorityRepository authorityRepository;


    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (alreadySetup)
            return;
        UserDataGenerator ud = new UserDataGenerator(userRepository, roleRepository, privilegeRepository, studentRepository, passwordEncoder, administratorRepository, authorityRepository);
        ud.createPrivileges();
        ud.createRoles();
        ud.createAdmin();
        ud.createAuthority();
        ud.createStudent();
        SubjectDataGenerator sd = new SubjectDataGenerator(subjectRepository, realisationRepository, passMethodRepository, teacherRepository);
        sd.insertSubjectsAndRealisations();
        alreadySetup = true;
    }
}
