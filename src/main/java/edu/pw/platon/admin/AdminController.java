package edu.pw.platon.admin;

import edu.pw.platon.user.Role;
import edu.pw.platon.user.User;
import edu.pw.platon.user.UserDto;
import javafx.util.Pair;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    AdminService adminService;

    @RequestMapping(value = "/addUser", method = RequestMethod.GET)
    public ModelAndView getAddUserView(@RequestParam(required = false) Boolean success,
                                       @RequestParam(required = false) Boolean error) {
        ModelAndView modelAndView = new ModelAndView("admin/addUser");
        return modelAndView;
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public RedirectView addNewUser(@RequestParam String firstName, @RequestParam(required = false) String secondName,
                                   @RequestParam String lastName, @Email @Valid @RequestParam String email, @RequestParam String role,
                                   @RequestParam String placeOfBirth, @RequestParam String idCardNo,
                                   @RequestParam(required = false) String roomNo, @RequestParam(required = false) String function,
                                   @RequestParam(required = false) Integer telephoneNo, @RequestParam(required = false) Integer internalNo,
                                   RedirectAttributes redirectAttributes) {

        if (validateAddUserRequest(firstName, lastName, placeOfBirth, idCardNo)){
            String result = adminService.addNewUser(role, firstName, secondName, lastName, email, placeOfBirth, idCardNo,
                        function, roomNo, telephoneNo, internalNo);
            return redirectToSuccessOrFailureUrl(result, "/admin/addUser", redirectAttributes);
        } else {
            return redirectToSuccessOrFailureUrl("Nieprawidłowe dane", "/admin/addUser", redirectAttributes);
        }
    }

    @RequestMapping(value = "/accountManagement", method = RequestMethod.GET)
    public ModelAndView getUsers(@RequestParam(required = false) String username, @RequestParam(required = false) Boolean success,
                                 @RequestParam(required = false) Boolean error){
        ModelAndView modelAndView = new ModelAndView("admin/accountManagement");
        List<UserDto> userList = adminService.getUsers(username);
        if (userList.size() == 1) modelAndView.addObject("chosenUser", userList.get(0));
        else if (!userList.isEmpty()) modelAndView.addObject("userList", userList);
        return modelAndView;
    }

    @RequestMapping(value = "/accountManagement/modify", method = RequestMethod.POST)
    public RedirectView modifyUser(@RequestParam String username, @RequestParam String firstName, @RequestParam(required = false) String secondName,
                                   @RequestParam(required = false) String lastName,  @Valid @Email @RequestParam(required = false) String email,
                                   @RequestParam(required = false) String roomNo, @RequestParam(required = false) String function,
                                   @RequestParam(required = false) Integer telephoneNo, @RequestParam(required = false) Integer internalNo,
                                   RedirectAttributes redirectAttributes){
        if (validateModifyUserParams(firstName, lastName)) {
            String result = adminService.modifyUser(username, firstName, secondName, lastName, email, function,
                    roomNo, telephoneNo, internalNo);
            return redirectToSuccessOrFailureUrl(result, "/admin/accountManagement", redirectAttributes);
        } else {
            return redirectToSuccessOrFailureUrl("Nieprawidłowe dane", "/admin/accountManagement", redirectAttributes);
        }
    }

    @RequestMapping(value = "/accountManagement/delete", method = RequestMethod.POST)
    public RedirectView deleteUser(Principal principal, RedirectAttributes redirectAttributes, @RequestParam String username){
        if (username.equals(principal.getName())) return redirectToSuccessOrFailureUrl("Nie możesz usunąć własnego konta.",
                "/admin/accountManagement", redirectAttributes);
        String result  = adminService.deleteUser(username);
        return redirectToSuccessOrFailureUrl(result, "/admin/accountManagement", redirectAttributes);
    }

    @RequestMapping(value = "/accountManagement/resetPassword", method = RequestMethod.POST)
    public RedirectView resetPassword(@RequestParam String username, @RequestParam String idCardNo, @RequestParam String placeOfBirth,
                                      RedirectAttributes redirectAttributes){
        if (!validatePasswordParams(idCardNo, placeOfBirth))
            return redirectToSuccessOrFailureUrl("Nieprawidłowe dane", "/admin/accountManagement", redirectAttributes);
        String result  = adminService.resetPassword(username, idCardNo, placeOfBirth);
        return redirectToSuccessOrFailureUrl(result, "/admin/accountManagement", redirectAttributes);
    }

    @RequestMapping(value = "/reports", method = RequestMethod.GET)
    public ModelAndView getReports() {
        return new ModelAndView("admin/reports");
    }

    @ModelAttribute("possibleRoles")
    private List<Role> getPossibleRoles() {
        return adminService.getRoles();
    }

    @ModelAttribute("accountData")
    private List<Pair<String, Integer>> getAccountData() {
        return adminService.countUsersByRole();
    }

    private RedirectView redirectToSuccessOrFailureUrl(String serviceResult, String url, RedirectAttributes redirectAttributes) {
        if (serviceResult.contains(AdminServiceImpl.SUCCESS_MESSAGE)){
            RedirectView redirectView = new RedirectView(url + "?success=true");
            redirectAttributes.addFlashAttribute("msg", serviceResult);
            return redirectView;
        }else {
            RedirectView redirectView = new RedirectView(url +  "?error=true");
            redirectAttributes.addFlashAttribute("msg", serviceResult);
            return redirectView;
        }
    }


    //TODO regexp?
    private boolean validateAddUserRequest(String firstName, String lastName, String placeOfBirth, String idCardNo){
        return !firstName.isEmpty() && !lastName.isEmpty() && validatePasswordParams(placeOfBirth, idCardNo);
    }

    private boolean validatePasswordParams(String placeOfBirth, String idCardNo){
        return !placeOfBirth.isEmpty() && placeOfBirth.length() > 2 && !idCardNo.isEmpty() && idCardNo.length() > 5;
    }

    private boolean validateModifyUserParams(String firstName, String lastName){
        return !firstName.isEmpty() && !lastName.isEmpty();
    }
}
