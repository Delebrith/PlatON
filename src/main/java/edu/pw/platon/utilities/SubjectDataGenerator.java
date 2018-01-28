package edu.pw.platon.utilities;

import edu.pw.platon.studies.*;
import edu.pw.platon.teacher.Teacher;
import edu.pw.platon.teacher.TeacherRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class SubjectDataGenerator {

    private final SubjectRepository subjectRepository;
    private final RealisationRepository realisationRepository;
    private final PassMethodRepository passMethodRepository;
    private final TeacherRepository teacherRepository;
    private final ClassTypeRepository classTypeRepository;
    private final ClassDateRepository classDateRepository;

    public SubjectDataGenerator(ClassDateRepository classDateRepository, ClassTypeRepository classTypeRepository, SubjectRepository subjectRepository, RealisationRepository realisationRepository, PassMethodRepository passMethodRepository, TeacherRepository teacherRepository) {
        this.subjectRepository = subjectRepository;
        this.realisationRepository = realisationRepository;
        this.passMethodRepository = passMethodRepository;
        this.teacherRepository = teacherRepository;
        this.classTypeRepository = classTypeRepository;
        this.classDateRepository= classDateRepository;
    }

    @Transactional
    public PassMethod createPassMethodIfNotFound(String passType, String description) {
        PassMethod passMethod = passMethodRepository.findOne(passType);
        if (passMethod == null) {
            passMethod = new PassMethod();
            passMethod.setName(passType);
            passMethod.setDescription(description);
            passMethodRepository.save(passMethod);
        }
        return passMethod;
    }

    @Transactional
    public List<Realisation> createRealisationsIfNotFound(Subject subject, String semesterCode) {
        List<Realisation> realisations = realisationRepository.findBySubjectAndSemesterCode(subject, semesterCode);
        if (realisations.isEmpty()) {
            Realisation rA = new Realisation();
            Realisation rB = new Realisation();
            rA.setSubject(subject);
            rB.setSubject(subject);
            rA.setName("A");
            rB.setName("B");
            rA.setSemesterCode(semesterCode);
            rB.setSemesterCode(semesterCode);
            rA.setTeachers(new HashSet<>());
            rB.setTeachers(new HashSet<>());
            realisationRepository.save(rA);
            realisationRepository.save(rB);
            return Arrays.asList(rA, rB);
        }
        return realisations;
    }

    @Transactional
    public Subject createSubjectIfNotFound(String code, String name, int ects, PassMethod passMethod, String description) {
        Subject subject = subjectRepository.findOne(code);
        if (subject == null) {
            subject = new Subject();
            subject.setCode(code);
            subject.setName(name);
            subject.setEcts(ects);
            subject.setPassMethod(passMethod);
            subject.setDescription(description);
            subjectRepository.save(subject);
        }
        return subject;
    }

    @Transactional
    public void insertSubjectsAndRealisations() {
        PassMethod examPass = createPassMethodIfNotFound("E", "Egzamin podczas sesji egzaminacyjnej");
        Subject subject = createSubjectIfNotFound("MAD", "Matematyka dyskretna", 3, examPass,
                "Kombinatoryka, grafy, dowodzenie twierdzeń grafowych");
        List<Realisation> realisations = createRealisationsIfNotFound(subject, "18L");
        Teacher teacherA = teacherRepository.findByUsername("teacher1");
        Teacher teacherB = teacherRepository.findByUsername("teacher2");
        teacherA.setRealisations(Arrays.asList(realisations.get(0)));
        teacherB.setRealisations(Arrays.asList(realisations.get(1)));
        teacherRepository.save(teacherA);
        teacherRepository.save(teacherB);

        PassMethod normalPass = createPassMethodIfNotFound("E", "Egzamin podczas sesji egzaminacyjnej");
        subject = createSubjectIfNotFound("BSS", "Bezpieczeństwo Systemów i Sieci", 3, normalPass,
                "Ataki typu SQL injection, systemy HoneyPot,  certyfikaty klucza publicznego");
        realisations = createRealisationsIfNotFound(subject, "18L");
        teacherA = teacherRepository.findByUsername("teacher3");
        teacherA.setRealisations(Arrays.asList(realisations.get(0)));
        realisations = createRealisationsIfNotFound(subject, "17L");
        teacherB = teacherRepository.findByUsername("teacher4");
        teacherB.setRealisations(Arrays.asList(realisations.get(1)));
        teacherRepository.save(teacherA);
        teacherRepository.save(teacherB);

        examPass = createPassMethodIfNotFound("B", "Zaliczenie na podstawie Kolokwiów i Laboratorii");
        subject = createSubjectIfNotFound("SCZR", "Systemy Czasu Rzeczywistego", 3, examPass,
                "Projektowanie systemów czasu rzeczywistego, synchronizacja, sieci przemysłowe, system QNX");
        realisations = createRealisationsIfNotFound(subject, "18L");
        teacherA = teacherRepository.findByUsername("teacher1");
        teacherA.setRealisations(Arrays.asList(realisations.get(0)));
        teacherRepository.save(teacherB);
    }

    @Transactional
    public void insertClassTypes() {
        Long id = 100L;
        createClassTypeIfNotFound("Wykład", id);
        createClassTypeIfNotFound("Ćwiczenia", ++id);
        createClassTypeIfNotFound("Laboratorium", ++id);
        createClassTypeIfNotFound("Projekt", ++id);
        createClassTypeIfNotFound("Seminarium", ++id);
    }

    @Transactional
    public ClassType createClassTypeIfNotFound(String name, Long id){
        ClassType classType = classTypeRepository.findByName(name);
        if (classType == null) {
            classType = new ClassType();
            classType.setName(name);
            classType.setId(id);
            classTypeRepository.save(classType);
        }
        return classType;
    }
    @Transactional
    public void insertClassDates() {
        ClassType lecture = classTypeRepository.findByName("Wykład");
        Subject subject1 = subjectRepository.findOne("MAD");
        List<Realisation> realisation1= realisationRepository.findBySubjectAndSemesterCode(subject1, "18L");
        createClassDatesIfNotFound(101L,lecture, "wtorek", "12:15", realisation1.get(0),"161");

        ClassType laboratory = classTypeRepository.findByName("Laboratorium");
        Subject subject2 = subjectRepository.findOne("BSS");
        List<Realisation> realisation2= realisationRepository.findBySubjectAndSemesterCode(subject2, "18L");
        createClassDatesIfNotFound(102L,laboratory, "poniedziałek", "14:15", realisation2.get(0),"138");

        ClassType project = classTypeRepository.findByName("Projekt");
        Subject subject3 = subjectRepository.findOne("SCZR");
        List<Realisation> realisation3= realisationRepository.findBySubjectAndSemesterCode(subject3, "18L");
        createClassDatesIfNotFound(103L,project, "piątek", "18:15", realisation3.get(0),"133");

    }

    @Transactional
    public ClassDate createClassDatesIfNotFound(Long id, ClassType classTypes, String dayOfTheWeek, String hour, Realisation realization, String room){
        ClassDate classDate = classDateRepository.findOne(id);
        if (classDate == null) {
            classDate = new ClassDate();
            classDate.setClassTypes(Arrays.asList(classTypes));
            classDate.setDayOfTheWeek(dayOfTheWeek);
            classDate.setHour(hour);
            classDate.setRealisation(realization);
            classDate.setRoom(room);
            classDate.setId(id);
            classDateRepository.save(classDate);
        }
        return classDate;
    }
}
