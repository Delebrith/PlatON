package edu.pw.platon.utilities;

import edu.pw.platon.admin.AdministratorRepository;
import edu.pw.platon.authority.AuthorityRepository;
import edu.pw.platon.office.OfficeEmployeeRepository;
import edu.pw.platon.student.StudentRepository;
import edu.pw.platon.studies.PassMethodRepository;
import edu.pw.platon.studies.RealisationRepository;
import edu.pw.platon.studies.SubjectRepository;
import edu.pw.platon.teacher.TeacherRepository;
import edu.pw.platon.user.PrivilegeRepository;
import edu.pw.platon.user.RoleRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class InitialDataLoader implements
        ApplicationListener<ContextRefreshedEvent> {

    private boolean alreadySetup = false;

    private final UserDataGenerator userDataGenerator;
    private final SubjectDataGenerator subjectDataGenerator;

    public InitialDataLoader(RoleRepository roleRepository, PrivilegeRepository privilegeRepository, StudentRepository studentRepository,
                             SubjectRepository subjectRepository, RealisationRepository realisationRepository,
                             PassMethodRepository passMethodRepository, TeacherRepository teacherRepository,
                             PasswordEncoder passwordEncoder, AdministratorRepository administratorRepository, AuthorityRepository authorityRepository,
                             OfficeEmployeeRepository officeEmployeeRepository) {
        userDataGenerator = new UserDataGenerator(roleRepository, privilegeRepository, studentRepository,
                                                  passwordEncoder, administratorRepository, authorityRepository, teacherRepository, officeEmployeeRepository);
        subjectDataGenerator = new SubjectDataGenerator(subjectRepository, realisationRepository, passMethodRepository, teacherRepository);
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (alreadySetup)
            return;
        userDataGenerator.createPrivileges();
        userDataGenerator.createRoles();
        userDataGenerator.insertUsers();
        subjectDataGenerator.insertSubjectsAndRealisations();
        alreadySetup = true;
    }
}
