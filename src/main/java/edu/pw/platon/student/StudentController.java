package edu.pw.platon.student;

import edu.pw.platon.admin.AdminServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

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

    @RequestMapping(value = "/enrollments", method = RequestMethod.GET)
    private ModelAndView getEnrollments(@RequestParam(required = false) String code) {
        ModelAndView modelAndView = new ModelAndView("student/enrollments");
        modelAndView.addObject("realisations", studentService.getRealisations(code));
        return modelAndView;
    }

    @RequestMapping(value = "/subjects", method = RequestMethod.GET)
    private ModelAndView getSubjects(Principal principal) {
        ModelAndView modelAndView = new ModelAndView("student/subjects");
        modelAndView.addObject("subjects", studentService.getMyCurrentSubjects(principal.getName()));
        return modelAndView;
    }

    @RequestMapping(value = "/enrollments", method = RequestMethod.POST)
    public RedirectView addEnrollView(Principal principal, @RequestParam String code, @RequestParam String subjectCode,
                                      @RequestParam String semesterCode, RedirectAttributes redirectAttributes) {
        String result = studentService.enroll(principal.getName(), code, subjectCode, semesterCode);
        return redirectToSuccessOrFailureUrl(result, "/enrollments", redirectAttributes);
    }

    @RequestMapping(value = "/subjects", method = RequestMethod.POST)
    public RedirectView addDismissEnrollView(Principal principal, @RequestParam String code, @RequestParam String subjectCode,
                                      @RequestParam String semesterCode, RedirectAttributes redirectAttributes) {
        String result = studentService.dismissEnroll(principal.getName(), code, subjectCode, semesterCode);
        return redirectToSuccessOrFailureUrl(result, "/subjects", redirectAttributes);
    }

    private RedirectView redirectToSuccessOrFailureUrl(String serviceResult, String url, RedirectAttributes redirectAttributes) {
        if (serviceResult.contains(AdminServiceImpl.SUCCESS_MESSAGE)){
            RedirectView redirectView = new RedirectView("/student" + url + "?success=true");
            redirectAttributes.addFlashAttribute("msg", serviceResult);
            return redirectView;
        }else {
            RedirectView redirectView = new RedirectView("/student" + url +  "?error=true");
            redirectAttributes.addFlashAttribute("msg", serviceResult);
            return redirectView;
        }
    }


}
