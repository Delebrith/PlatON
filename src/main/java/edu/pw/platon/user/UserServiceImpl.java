package edu.pw.platon.user;

import edu.pw.platon.studies.*;
import edu.pw.platon.user.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final SubjectRepository subjectRepository;
    private final RealisationRepository realisationRepository;
    private final DegreeCourseRepository degreeCourseRepository;
    private final RequirementRepository requirementRepository;

    UserServiceImpl(@Autowired UserRepository userRepository,
                    @Autowired SubjectRepository subjectRepository,
                    @Autowired RealisationRepository realisationRepository,
                    @Autowired DegreeCourseRepository degreeCourseRepository,
                    @Autowired RequirementRepository requirementRepository){
        this.userRepository = userRepository;
        this.subjectRepository = subjectRepository;
        this.realisationRepository = realisationRepository;
        this.degreeCourseRepository = degreeCourseRepository;
        this.requirementRepository = requirementRepository;
    }

    @Override
    public Response login(String username, String passwordhash) {

        User user = userRepository.findOne(username);
        if (user == null || !user.getPasswordHash().equals(passwordhash)){
            Response response = new Response();
            response.setDescription("Wrong user's credentials");
            return response;
        }
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setDescription(Response.SUCCESS_MESSAGE);
        loginResponse.setUser(user);
        return loginResponse;
    }

    @Override
    public void logout(User user) {

    }

    @Override
    public Response changePassword(String username, String oldPasswordHash, String newPasswordHash) {
        User user = userRepository.findOne(username);
        if (user == null || !user.getPasswordHash().equals(oldPasswordHash)){
            Response response = new Response();
            response.setDescription("Wrong user's credentials");
            return response;
        }
        user.setPasswordHash(newPasswordHash);
        userRepository.save(user);
        Response response = new Response();
        response.setDescription(Response.SUCCESS_MESSAGE);
        return response;
    }

    @Override
    public Response getRequirementsInfo(String courseId) {
        DegreeCourse degreeCourse = degreeCourseRepository.findOne(courseId);
        if (degreeCourse == null){
            Response response = new Response();
            response.setDescription("Degree course not found");
        }
        List<Requirement> requirements = requirementRepository.findByCourse(degreeCourse);
        RequirementsInfoResponse requirementsInfoResponse = new RequirementsInfoResponse();
        List<RequirementsInfoResponse.RequirementsInfo> requirementsInfoList = new ArrayList<>();
        for (Requirement requirement:
             requirements) {
            RequirementsInfoResponse.RequirementsInfo requirementsInfo = new RequirementsInfoResponse.RequirementsInfo();
            requirementsInfo.setSemestrNo(requirement.getSemesterNo());
            requirementsInfo.setSubjects((Subject[]) requirement.getSubjects().toArray());
            requirementsInfoList.add(requirementsInfo);
        }
        requirementsInfoResponse.setCourse(courseId);
        requirementsInfoResponse.setDescription(Response.SUCCESS_MESSAGE);
        return requirementsInfoResponse;
    }

    @Override
    public Response getScheduleInfo(String semesterCode, String subjectCode) {
        Subject subject = subjectRepository.findOne(subjectCode);
        if (subject == null){
            Response response = new Response();
            response.setDescription("No such subject");
        }
        List<Realisation> realisations = realisationRepository.findBySubjectAndSemesterCode(subject, semesterCode);
        if (realisations == null || realisations.isEmpty()){
            Response response = new Response();
            response.setDescription("No realisations for this subject in given semester");
        }
        List<ClassDate> classDateList = new ArrayList<>();
        for (Realisation realisation:
             realisations) {
            classDateList.addAll(realisation.getClassDates());
        }
        if (classDateList.isEmpty()){
            Response response = new Response();
            response.setDescription("No classes scheduled for this realisation");
        }
        ScheduleInfoResponse scheduleInfoResponse = new ScheduleInfoResponse();
        scheduleInfoResponse.setClassDates((ClassDate[]) classDateList.toArray());
        scheduleInfoResponse.setDescription(Response.SUCCESS_MESSAGE);
        return scheduleInfoResponse;
    }

    @Override
    public Response getSubjectInfo(String subjectCode) {
        Subject subject = subjectRepository.findOne(subjectCode);
        if (subject == null){
            Response response = new Response();
            response.setDescription("Subject not found");
            return response;
        }
        SubjectInfoResponse subjectInfoResponse = new SubjectInfoResponse();
        subjectInfoResponse.setCode(subjectCode);
        subjectInfoResponse.setEcts(subject.getEcts());
        subjectInfoResponse.setDescription(subject.getDescription());
        subjectInfoResponse.setName(subject.getName());
        subjectInfoResponse.setPassMethod(subject.getPassMethod().getName());
        subjectInfoResponse.setRealisations((Realisation[]) subject.getRealisations().toArray());
        subjectInfoResponse.setDescription(Response.SUCCESS_MESSAGE);
        return subjectInfoResponse;
    }
}
