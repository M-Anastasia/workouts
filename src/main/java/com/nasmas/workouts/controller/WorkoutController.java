package com.nasmas.workouts.controller;

import com.nasmas.workouts.model.MuscleGroup;
import com.nasmas.workouts.model.Users;
import com.nasmas.workouts.model.Workout;
import com.nasmas.workouts.model.WorkoutType;
import com.nasmas.workouts.model.local.WorkoutLocal;
import com.nasmas.workouts.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
public class WorkoutController {

    @Autowired
    private WorkoutService workoutService;
    @Autowired
    private WorkoutTypeService workoutTypeService;
    @Autowired
    private MuscleGroupService muscleGroupService;
    @Autowired
    private UsersService usersService;
    @Autowired
    private UtilService utilService;

    @PostMapping("/createWorkout")
    public String createWorkout(WorkoutLocal workoutLocal, ModelMap model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) auth.getPrincipal();

        model = utilService.getBaseModel(model);

        List<MuscleGroup> muscleGroups = new ArrayList<>();
        for (String muscleGroupUuid : workoutLocal.getMuscleGroup()) {
            muscleGroups.add(muscleGroupService.getMuscleGroupByUuid(UUID.fromString(muscleGroupUuid)));
        }
        WorkoutType workoutType = workoutTypeService.getWorkoutTypeByUuid(UUID.fromString(workoutLocal.getWorkoutType()));
        Workout workout = new Workout(workoutLocal, workoutType, muscleGroups, usersService.findByName(userDetails.getUsername()));
        workoutService.createWorkout(workout);

        return "redirect:index";
    }

    @GetMapping("/workout/{uniqueId}")
    public String getWorkoutsByCouchUuid(@PathVariable Long uniqueId, ModelMap model) {
        model = utilService.getBaseModel(model);
        Workout workout = workoutService.getWorkout(uniqueId);
        model.addAttribute("workout", workout);
        return "workout";
    }

//    @GetMapping("/workout/{uniqueId}")
//    public String getWorkout(@PathVariable Long uniqueId, ModelMap model) {
//
//        model = utilService.getBaseModel(model);
//        return "workout";
//    }
}
