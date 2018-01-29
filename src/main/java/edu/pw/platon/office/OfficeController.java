package edu.pw.platon.office;

import edu.pw.platon.admin.AdminServiceImpl;
import edu.pw.platon.student.FinancialObligationDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/office")
@Secured(value = "ROLE_OFFICE")
public class OfficeController {

    @Autowired
    private OfficeService officeService;

    @RequestMapping(value = "/changeEnrollments", method = RequestMethod.GET)
    public ModelAndView getEnrollmentsView(@RequestParam(required = false) Boolean success,
                                       @RequestParam(required = false) Boolean error) {
        ModelAndView modelAndView = new ModelAndView("office/changeEnrollments");
        return modelAndView;
    }

    @RequestMapping(value = "/financialObligation/add", method = RequestMethod.GET)
    public ModelAndView getAddObligationView() {
        return new ModelAndView("office/addFinancialObligation");
    }

    @RequestMapping(value = "/financialObligation/find", method = RequestMethod.GET)
    public ModelAndView findObligation(@RequestParam(required = false) Integer studentBookNo) {
        ModelAndView modelAndView = new ModelAndView("office/findFinancialObligation");
        modelAndView.addObject("foundObligations", officeService.listObligations(studentBookNo));
        return modelAndView;
    }

    @ModelAttribute("allObligations")
    List<FinancialObligationDto> getAllObligations() {
        return officeService.listObligations(null);
    }

    @RequestMapping(value = "/financialObligation/add", method = RequestMethod.POST)
    public RedirectView addObligation(@RequestParam Integer studentBookNo, @RequestParam String type, @RequestParam String accountNo,
                                      @RequestParam Float amount, @RequestParam String dueDate, RedirectAttributes redirectAttributes) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-DD");
        Date date = dateFormat.parse(dueDate);
        String result = officeService.addObligation(studentBookNo, amount, type, accountNo, date);
        return redirectToSuccessOrFailureUrl(result, "/financialObligation/add", redirectAttributes);
    }

    @RequestMapping(value = "/financialObligation/delete", method = RequestMethod.POST)
    public RedirectView addObligationView(@RequestParam Long id, RedirectAttributes redirectAttributes) {
        String result = officeService.deleteObligation(id);
        return redirectToSuccessOrFailureUrl(result, "/financialObligation/find", redirectAttributes);
    }

    private RedirectView redirectToSuccessOrFailureUrl(String serviceResult, String url, RedirectAttributes redirectAttributes) {
        if (serviceResult.contains(AdminServiceImpl.SUCCESS_MESSAGE)){
            RedirectView redirectView = new RedirectView("/office" + url + "?success=true");
            redirectAttributes.addFlashAttribute("msg", serviceResult);
            return redirectView;
        }else {
            RedirectView redirectView = new RedirectView("/office" + url +  "?error=true");
            redirectAttributes.addFlashAttribute("msg", serviceResult);
            return redirectView;
        }
    }

}
