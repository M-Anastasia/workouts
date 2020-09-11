package com.nasmas.workouts.controller;

import com.nasmas.workouts.model.MuscleGroup;
import com.nasmas.workouts.model.Users;
import com.nasmas.workouts.model.Workout;
import com.nasmas.workouts.model.WorkoutType;
import com.nasmas.workouts.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.Optional;

@Controller
public class RegistrationController {

    @Autowired
    private UsersService userService;
    @Autowired
    private SecurityService securityService;
    @Autowired
    private UtilService utilService;

    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @GetMapping("/successful_registration")
    public String registrationSuccess(ModelMap model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        model.addAttribute("username", userDetails.getUsername());
        return "successful_registration";
    }

    @PostMapping("/registration")
    public RedirectView registration(Users userForm, RedirectAttributes redirectAttributes) {
        userForm.setPassword(userService.getSaltString());
        userForm.setPasswordConfirm(userForm.getPassword());
        userService.saveUser(userForm);
        securityService.autoLogin(userForm.getUsername(), userForm.getPasswordConfirm());
        RedirectView redirectView = new RedirectView();
        redirectView.setContextRelative(true);
        redirectView.setUrl("successful_registration");
        redirectAttributes.addFlashAttribute("password", userForm.getPasswordConfirm());
        return redirectView;
    }

    @GetMapping({"/", "/index"})
    public String welcome(ModelMap model, @RequestParam("page") Optional<Integer> page, @RequestParam("size")
            Optional<Integer> size) {
        model = utilService.getBaseModel(model, page, size);
        return "main";
    }

    @GetMapping("/profile")
    public String profile(ModelMap model, @RequestParam("page") Optional<Integer> page, @RequestParam("size")
            Optional<Integer> size) {
        model = utilService.getBaseModel(model, page, size);
        return "profile";
    }
}
