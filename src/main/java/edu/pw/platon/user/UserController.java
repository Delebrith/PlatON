package edu.pw.platon.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/home")
    public ModelAndView home(Principal principal){
        ModelAndView modelAndView = new ModelAndView("home");
        if (principal != null) {
            User user = userRepository.findOne(principal.getName());
            String name = "";
            if (user.getFirstName() != null) name = name + user.getFirstName() + " ";
            if (user.getSecondName() != null) name = name + user.getSecondName() + " ";
            if (user.getLastName() != null) name = name + user.getLastName() + " ";
            modelAndView.addObject("name", name);
            modelAndView.addObject("email", user.getEmail());
            String roles = "";
            for (Role role:
                 user.getRoles()) {
                roles = roles + role.getDisplayName() + " ";
            }
            modelAndView.addObject("role", roles);
        }
        return modelAndView;
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
