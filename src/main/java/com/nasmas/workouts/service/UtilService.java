package com.nasmas.workouts.service;

import com.nasmas.workouts.model.MuscleGroup;
import com.nasmas.workouts.model.Users;
import com.nasmas.workouts.model.Workout;
import com.nasmas.workouts.model.WorkoutType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.util.List;

@Service
public class UtilService {

    @Autowired
    private UsersService userService;
    @Autowired
    private WorkoutTypeService workoutTypeService;
    @Autowired
    private MuscleGroupService muscleGroupService;
    @Autowired
    private WorkoutService workoutService;

    public ModelMap getBaseModel(ModelMap model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        List<WorkoutType> workoutTypes = workoutTypeService.getWorkoutTypeList();
        List<MuscleGroup> muscleGroups = muscleGroupService.getMuscleGroupList();
        List<Workout> workouts = workoutService.getWorkoutsList();
        model.addAttribute("workoutPage", workouts);
        if (!auth.getPrincipal().equals("anonymousUser")) {
            UserDetails userDetails = (UserDetails) auth.getPrincipal();
            Users user = userService.findByName(userDetails.getUsername());
            List<Workout> workoutsByCouch = workoutService.getWorkoutsList(user);
            model.addAttribute("workoutsByCouch", workoutsByCouch);
            model.addAttribute("username", user.getName());
            model.addAttribute("workoutTypes", workoutTypes);
            model.addAttribute("muscleGroups", muscleGroups);
        }
        return model;
    }
}
