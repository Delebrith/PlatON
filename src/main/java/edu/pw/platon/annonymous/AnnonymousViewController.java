package edu.pw.platon.annonymous;

import edu.pw.platon.annonymous.api.Response;
import edu.pw.platon.annonymous.api.SubjectInfoDto;
import edu.pw.platon.studies.Realisation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/annonymousUser")
public class AnnonymousViewController {
    @Autowired
    private
    AnnonymousService annonymousService;

    @GetMapping("/subjectInfo")
    public ModelAndView getSubjectInfo(@RequestParam(required = false) String subjectCode) {
        ModelAndView modelAndView = new ModelAndView("annonymousUser/subjectInfo");
        Response response;
        if(subjectCode != null) {
            response = annonymousService.getSubjectInfo(subjectCode);
            if(response.getDescription().equals("Nie ma takiego przedmiotu")) {
                modelAndView.addObject("name", "Subject not found");
                return modelAndView;
            }
            modelAndView.addObject("subjectCode", subjectCode);
            modelAndView.addObject("name", ((SubjectInfoDto)response).getName());
            modelAndView.addObject("ects", ((SubjectInfoDto)response).getEcts());
            modelAndView.addObject("passMethod", ((SubjectInfoDto)response).getPassMethod());
            String subjectDescription = ((SubjectInfoDto)response).getSubjectDescription();
            modelAndView.addObject("description", subjectDescription);
            String realisations = "";
            for(Realisation realisation:((SubjectInfoDto)response).getRealisations()) {
                realisations += realisation.getSemesterCode() + ", ";
            }
            modelAndView.addObject("realisations", realisations);
        }
        return modelAndView;
    }

    @GetMapping("/usefullLinks")
    public ModelAndView getUsefulLinks() {
        return new ModelAndView("annonymousUser/usefullLinks");
    }



}
