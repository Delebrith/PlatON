package edu.pw.platon.annonymous;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
//@RequestMapping("/annonymous")
public class AnnonymousViewController {
    @GetMapping("/subjectInfo")
    public ModelAndView getSubjectInfo(@RequestParam(required = false) String subjectCode) {

        return new ModelAndView("subjectInfo");
    }

}
