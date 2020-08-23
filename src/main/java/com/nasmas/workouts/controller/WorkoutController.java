package com.nasmas.workouts.controller;

import com.nasmas.workouts.model.MuscleGroup;
import com.nasmas.workouts.model.Workout;
import com.nasmas.workouts.model.WorkoutType;
import com.nasmas.workouts.model.local.WorkoutLocal;
import com.nasmas.workouts.service.MuscleGroupService;
import com.nasmas.workouts.service.WorkoutService;
import com.nasmas.workouts.service.WorkoutTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;

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

    @PostMapping("/createWorkout")
    public String createWorkout(WorkoutLocal workoutLocal, ModelMap model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        List<WorkoutType> workoutTypes = workoutTypeService.getWorkoutTypeList();
        List<MuscleGroup> muscleGroupss = muscleGroupService.getMuscleGroupList();
        List<Workout> workouts = workoutService.getWorkoutsList();

        List<MuscleGroup> muscleGroups = new ArrayList<>();
        for (String muscleGroupUuid : workoutLocal.getMuscleGroup()) {
            muscleGroups.add(muscleGroupService.getMuscleGroupByUuid(UUID.fromString(muscleGroupUuid)));
        }
        WorkoutType workoutType = workoutTypeService.getWorkoutTypeByUuid(UUID.fromString(workoutLocal.getWorkoutType()));
        Workout workout = new Workout(workoutLocal, workoutType, muscleGroups);
        workoutService.createWorkout(workout);
        model.addAttribute("username", userDetails.getUsername());
        model.addAttribute("workoutTypes", workoutTypes);
        model.addAttribute("muscleGroups", muscleGroupss);
        model.addAttribute("workoutPage", workouts);
        return "redirect:index";
    }
}
