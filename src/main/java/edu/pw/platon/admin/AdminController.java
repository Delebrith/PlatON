package edu.pw.platon.admin;

import edu.pw.platon.user.Role;
import edu.pw.platon.user.User;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    AdminService adminService;

    @RequestMapping(value = "/addUser", method = RequestMethod.GET)
    public ModelAndView getAddUserView(@RequestParam(required = false) String role,
                                       @RequestParam(required = false) Boolean success,
                                       @RequestParam(required = false) Boolean error) {
        log.info("get");
        return new ModelAndView("admin/addUser");
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public RedirectView addNewUser(@RequestParam String firstName, @RequestParam(required = false) String secondName,
                                   @RequestParam String lastName, @Email @Valid @RequestParam String email, @RequestParam String role,
                                   @RequestParam String placeOfBirth, @RequestParam String idCardNo,
                                   @RequestParam(required = false) String roomNo, @RequestParam(required = false) String function,
                                   @RequestParam(required = false) Integer telephoneNo, @RequestParam(required = false) Integer internalNo) {

        if (validateAddUserRequest(firstName, lastName, placeOfBirth, idCardNo) &&
                adminService.addNewUser(role, firstName, secondName, lastName, email, placeOfBirth, idCardNo, function, roomNo, telephoneNo, internalNo)) {
            return new RedirectView("/admin/addUser?success=true");
        } else {
            return new RedirectView("/admin/addUser?error=true");
        }
    }

    @RequestMapping(value = "/accountManagement", method = RequestMethod.GET)
    public ModelAndView getUsers(@RequestParam(required = false) String username){
        ModelAndView modelAndView = new ModelAndView("admin/getUsers");
        List<User> userList = adminService.getUsers(username);
        if (userList.size() == 1) modelAndView.addObject("chosenUser", userList.get(0));
        else if (!userList.isEmpty()) modelAndView.addObject("userList", userList);
        return modelAndView;
    }

    @RequestMapping(value = "/accountManagement/modify", method = RequestMethod.POST)
    public RedirectView modifyUser(@RequestParam String username, @RequestParam String firstName, @RequestParam(required = false) String secondName,
                                   @RequestParam String lastName,  @Valid @Email @RequestParam String email,
                                   @RequestParam(required = false) String roomNo, @RequestParam(required = false) String function,
                                   @RequestParam(required = false) Integer telephoneNo, @RequestParam(required = false) Integer internalNo){
        if (validateMofidyUserRequest(firstName, lastName) &&
                adminService.modifyUser(username, firstName, secondName, lastName, email, function, roomNo, telephoneNo, internalNo)) {
            return new RedirectView("/admin/addUser?success=true");
        } else {
            return new RedirectView("/admin/addUser?error=true");
        }
    }

    @ModelAttribute("possibleRoles")
    private List<Role> getPossibleRoles() {
        return adminService.getRoles();
    }


    //TODO regexp?
    private boolean validateAddUserRequest(String firstName, String lastName, String placeOfBirth, String idCardNo){
        return !firstName.isEmpty() && !lastName.isEmpty() && !placeOfBirth.isEmpty() && placeOfBirth.length() > 2 && !idCardNo.isEmpty() &&
                idCardNo.length() > 5;
    }

    private boolean validateMofidyUserRequest(String firstName, String lastName){
        return !firstName.isEmpty() && !lastName.isEmpty();
    }
}
