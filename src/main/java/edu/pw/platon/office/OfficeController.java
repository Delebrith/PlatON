package edu.pw.platon.office;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
@RequestMapping("/office")
public class OfficeController {

    @Autowired
    private OfficeService officeService;

    @RequestMapping(value = "/changeEnrollments", method = RequestMethod.GET)
    public ModelAndView getEnrollmentsView(@RequestParam(required = false) Boolean success,
                                       @RequestParam(required = false) Boolean error) {
        ModelAndView modelAndView = new ModelAndView("office/changeEnrollments");
        return modelAndView;
    }


}
