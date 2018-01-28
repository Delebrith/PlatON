package edu.pw.platon.annonymous;

import edu.pw.platon.annonymous.api.Response;
import edu.pw.platon.annonymous.api.SubjectInfoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
//@RequestMapping("/annonymous")
public class AnnonymousViewController {
    @Autowired
    AnnonymousService annonymousService;

    @GetMapping("/subjectInfo")
    public ModelAndView getSubjectInfo(@RequestParam(required = false) String subjectCode) {
        ModelAndView modelAndView = new ModelAndView("subjectInfo");
        SubjectInfoResponse response;
        if(subjectCode != null) {
            response = (SubjectInfoResponse) annonymousService.getSubjectInfo(subjectCode);
            if(response.getDescription().equals("Subject not found"))
            {
                modelAndView.addObject("name", "Subject not found");
                return modelAndView;
            }
            modelAndView.addObject("name", response.getName());
        }
        return modelAndView;
    }



}
