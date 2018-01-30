package edu.pw.platon.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @RequestMapping(value = "/myFinances", method = RequestMethod.GET)
    private ModelAndView getMyFinances(Principal principal) {
        ModelAndView modelAndView = new ModelAndView("student/myFinances");
        modelAndView.addObject("allMyFinances", studentService.getMyFinancialObligations(principal.getName()));
        return modelAndView;
    }
}
