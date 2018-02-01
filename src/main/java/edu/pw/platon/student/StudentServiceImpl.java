package edu.pw.platon.student;

import edu.pw.platon.studies.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final RealisationRepository realisationRepository;
    private final SubjectRepository subjectRepository;
    private final EnrollmentRepository enrollmentRepository;

    public static final String SUCCESS_MESSAGE = "Sukces! ";

    public StudentServiceImpl(StudentRepository studentRepository, RealisationRepository realisationRepository, SubjectRepository subjectRepository,
                              EnrollmentRepository enrollmentRepository) {
        this.studentRepository = studentRepository;
        this.realisationRepository = realisationRepository;
        this.subjectRepository = subjectRepository;
        this.enrollmentRepository = enrollmentRepository;
    }

    @Override
    public List<FinancialObligationDto> getMyFinancialObligations(String username) {
        Student student = studentRepository.findOne(username);
        if (student == null) return null; // should never happen
        Collection<FinancialObligation> financialObligations = student.getFinancialObligations();
        List<FinancialObligationDto> financialObligationDtos = new ArrayList<>();
        for (FinancialObligation financialObligation:
             financialObligations) {
            String name = student.getFirstName();
            if (student.getSecondName() != null) name = name + " " + student.getSecondName();
            name = name + " " + student.getLastName();
            financialObligationDtos.add(FinancialObligationDto.builder()
                    .id(financialObligation.getId())
                    .studentName(name)
                    .type(financialObligation.getType())
                    .amount(financialObligation.getAmount())
                    .dueDate(financialObligation.getDueDate())
                    .accountNo(financialObligation.getAccountNo())
                    .studentBookNo(student.getStudentBookNo())
                    .build());
        }
        return financialObligationDtos;
    }

    @Override
    public List<RealisationDto> getRealisations(String code) {
        LocalDateTime date = LocalDateTime.now();
        int year = date.getYear();
        int month = date.getMonthValue();
        int day = date.getDayOfMonth();
        String semesterCode;
        if (month >= 11) {
            int cutYear = year % 100 + 1;
            semesterCode = cutYear + "L";
        }
        else if (month <= 2 || (month == 3 && day <= 15)) {
            int cutYear = year % 100;
            semesterCode = cutYear + "L";
        }
        else {
            int cutYear = year % 100;
            semesterCode = cutYear + "Z";
        }
        if (code == null || code.equals("")) {
            List<RealisationDto> realisationDtos = new ArrayList<>();
            Iterable<Realisation> realisations = realisationRepository.findBySemesterCode(semesterCode);
            for (Realisation realisation : realisations) {
                realisationDtos.add(RealisationDto.builder()
                        .code(realisation.getSubject().getCode())
                        .realName(realisation.getName())
                        .name(realisation.getSubject().getName())
                        .ects(realisation.getSubject().getEcts())
                        .semCode(semesterCode)
                        .passMethod(realisation.getSubject().getPassMethod().getName())
                        .build());
            }
            return realisationDtos;
        }
        Subject subject = subjectRepository.findOne(code);
        if (subject == null) return new ArrayList<>();
        List<Realisation> realisations = realisationRepository.findBySubjectAndSemesterCode(subject, semesterCode);
        if (realisations == null) return new ArrayList<>();
        List<RealisationDto> realisationDtos = new ArrayList<>();
        for (Realisation realisation : realisations) {
            realisationDtos.add(RealisationDto.builder()
                    .code(realisation.getSubject().getCode())
                    .realName(realisation.getName())
                    .name(realisation.getSubject().getName())
                    .ects(realisation.getSubject().getEcts())
                    .semCode(semesterCode)
                    .passMethod(realisation.getSubject().getPassMethod().getName())
                    .build());
        }
        return realisationDtos;
    }

    @Override
    @Transactional
    public String enroll(String username, String realCode, String subjectCode, String semesterCode) {
        Student student = studentRepository.findOne(username);
        if (student == null) return "Nie odnaleziono studenta!"; // should not happen
        Realisation realisation = realisationRepository.findBySubjectAndSemesterCodeAndName(subjectRepository.findOne(subjectCode), semesterCode, realCode);
        if (realisation == null) return "Wybrano złą realizację!"; // should not happen
        List<Enrollment> enrollments = enrollmentRepository.findByStudent(student);
        for (Enrollment enrollment : enrollments)
        if (enrollment.getRealisation().getSemesterCode().equals(semesterCode) && enrollment.getRealisation().getSubject().getCode().equals(subjectCode))
                return "Zapis na dany przedmiot istnieje już w systemie!";
        Enrollment enrollment = new Enrollment();
        enrollment.setStudent(student);
        enrollment.setRealisation(realisation);
        enrollment.setStatus("Oczekujący");
        enrollmentRepository.save(enrollment);
        return SUCCESS_MESSAGE + "Zapisano się na przedmiot " + realisation.getSubject().getCode();
    }

    @Override
    @Transactional
    public String dismissEnroll(String username, String realCode, String subjectCode, String semesterCode) {
        Student student = studentRepository.findOne(username);
        if (student == null) return "Nie odnaleziono studenta!"; // should not happen
        Realisation realisation = realisationRepository.findBySubjectAndSemesterCodeAndName(subjectRepository.findOne(subjectCode), semesterCode, realCode);
        if (realisation == null) return "Wybrano złą realizację!"; // should not happen
        Enrollment enrollment = enrollmentRepository.findByStudentAndRealisation(student, realisation);
        if (enrollment == null) return "Nie odnaleziono zapisu w systemie!"; // should not happen
        String enrollStatus = enrollment.getStatus();
        if (!enrollStatus.equals("Oczekujący") && !enrollStatus.equals("Rezerwowy"))
            return "Nie można wypisać się z zapisanego bądź zakońćzonego przedmiotu!";
        enrollmentRepository.delete(enrollment);
        return SUCCESS_MESSAGE + "Usunięto zapis na przedmiot " + realisation.getSubject().getCode();
    }

    @Override
    public List<EnrollmentDto> getMyCurrentSubjects(String username) {
        Student student = studentRepository.findOne(username);
        if (student == null) return new ArrayList<>(); // should not happen
        Iterable<Enrollment> enrollments = enrollmentRepository.findByStudent(student);
        List <EnrollmentDto> currentEnrollments = new ArrayList<>();
        for (Enrollment enrollment : enrollments) {
            currentEnrollments.add(EnrollmentDto.builder()
                    .subjectCode(enrollment.getRealisation().getSubject().getCode())
                    .realCode(enrollment.getRealisation().getName())
                    .subjectName(enrollment.getRealisation().getSubject().getName())
                    .ects(enrollment.getRealisation().getSubject().getEcts())
                    .passMethod(enrollment.getRealisation().getSubject().getPassMethod().getName())
                    .semCode(enrollment.getRealisation().getSemesterCode())
                    .enrollStatus(enrollment.getStatus())
                    .build());
        }
        return currentEnrollments;
    }
}
