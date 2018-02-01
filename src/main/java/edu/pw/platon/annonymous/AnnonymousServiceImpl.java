package edu.pw.platon.annonymous;

import edu.pw.platon.annonymous.api.RequirementsInfoDto;
import edu.pw.platon.annonymous.api.Response;
import edu.pw.platon.annonymous.api.ScheduleInfoDto;
import edu.pw.platon.annonymous.api.SubjectInfoDto;
import edu.pw.platon.studies.ClassDate;
import edu.pw.platon.studies.DegreeCourse;
import edu.pw.platon.studies.DegreeCourseRepository;
import edu.pw.platon.studies.Realisation;
import edu.pw.platon.studies.RealisationRepository;
import edu.pw.platon.studies.Requirement;
import edu.pw.platon.studies.RequirementRepository;
import edu.pw.platon.studies.Subject;
import edu.pw.platon.studies.SubjectRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AnnonymousServiceImpl implements AnnonymousService {

    private final SubjectRepository subjectRepository;
    private final RealisationRepository realisationRepository;
    private final DegreeCourseRepository degreeCourseRepository;
    private final RequirementRepository requirementRepository;

    AnnonymousServiceImpl(SubjectRepository subjectRepository,
                          RealisationRepository realisationRepository,
                          DegreeCourseRepository degreeCourseRepository,
                          RequirementRepository requirementRepository){
        this.subjectRepository = subjectRepository;
        this.realisationRepository = realisationRepository;
        this.degreeCourseRepository = degreeCourseRepository;
        this.requirementRepository = requirementRepository;
    }

    @Override
    public Response getRequirementsInfo(String courseId) {
        DegreeCourse degreeCourse = degreeCourseRepository.findOne(courseId);
        if (degreeCourse == null){
            Response response = new Response();
            response.setDescription("Nie ma takiego kierunku");
        }
        List<Requirement> requirements = requirementRepository.findByCourse(degreeCourse);
        RequirementsInfoDto requirementsInfoDto = new RequirementsInfoDto();
        List<RequirementsInfoDto.RequirementsInfo> requirementsInfoList = new ArrayList<>();
        for (Requirement requirement:
             requirements) {
            RequirementsInfoDto.RequirementsInfo requirementsInfo = new RequirementsInfoDto.RequirementsInfo();
            requirementsInfo.setSemestrNo(requirement.getSemesterNo());
            requirementsInfo.setSubjects((Subject[]) requirement.getSubjects().toArray());
            requirementsInfoList.add(requirementsInfo);
        }
        requirementsInfoDto.setCourse(courseId);
        requirementsInfoDto.setDescription(Response.SUCCESS_MESSAGE);
        return requirementsInfoDto;
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
        for (Realisation realisation: realisations) {
            classDateList.addAll(realisation.getClassDates());
        }
        if (classDateList.isEmpty()){
            Response response = new Response();
            response.setDescription("No classes scheduled for this realisation");
        }
        ScheduleInfoDto scheduleInfoDto = new ScheduleInfoDto();
        scheduleInfoDto.setClassDates((ClassDate[]) classDateList.toArray());
        scheduleInfoDto.setDescription(Response.SUCCESS_MESSAGE);
        return scheduleInfoDto;
    }

    @Override
    public Response getSubjectInfo(String subjectCode) {
        Subject subject = subjectRepository.findOne(subjectCode);
        if (subject == null){
            Response response = new Response();
            response.setDescription("Nie ma takiego przedmiotu");
            return response;
        }
        SubjectInfoDto subjectInfoDto = new SubjectInfoDto();
        subjectInfoDto.setCode(subjectCode);
        subjectInfoDto.setEcts(subject.getEcts());
        String descr = subject.getDescription();
        subjectInfoDto.setSubjectDescription(descr);
        subjectInfoDto.setName(subject.getName());
        subjectInfoDto.setPassMethod(subject.getPassMethod().getName());
        Realisation[] realisations = subject.getRealisations().toArray(new Realisation[subject.getRealisations().size()]);
        subjectInfoDto.setRealisations(realisations);
        subjectInfoDto.setDescription(Response.SUCCESS_MESSAGE);
        return subjectInfoDto;
    }
}
