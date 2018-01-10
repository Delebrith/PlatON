package edu.pw.platon.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {
    @GetMapping("/home")
    public ModelAndView home(){
        return new ModelAndView("home");
    }

    @GetMapping("/login")
    public ModelAndView login(@RequestParam(required = false) boolean error,
                              @RequestParam(required = false) boolean logout) {
        return new ModelAndView("login");
    }

    @GetMapping("/logoutScreen")
    public ModelAndView logoutScreen(@RequestParam(required = false) boolean logoutSuccess){
        return new ModelAndView("logout");
    }

}
