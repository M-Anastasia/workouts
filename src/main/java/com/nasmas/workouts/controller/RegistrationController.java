package com.nasmas.workouts.controller;

import com.nasmas.workouts.model.Users;
import com.nasmas.workouts.service.SecurityService;
import com.nasmas.workouts.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class RegistrationController {

    @Autowired
    private UsersService userService;
    @Autowired
    private SecurityService securityService;

    @GetMapping("/login")
    public String registration(Model model) {
        return "login";
    }

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String registration(Users userForm) {
        if (userForm != null && userForm.getPassword() != null && userForm.getPasswordConfirm() != null) {
            if (!userForm.getPassword().equals(userForm.getPasswordConfirm())){
                return "registration";
            }
        }
        userService.saveUser(userForm);

        securityService.autoLogin(userForm.getUsername(), userForm.getPasswordConfirm());

        return "redirect:/index";
    }

    @GetMapping({"/", "/index"})
    public String welcome(ModelMap model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) auth.getPrincipal();

        if (userDetails != null) {
            Users user = userService.findByName(userDetails.getUsername());
            model.addAttribute("username", user.getName());
        }
        return "index";
    }
}
