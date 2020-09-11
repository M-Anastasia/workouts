package com.nasmas.workouts.controller;

import com.nasmas.workouts.model.MuscleGroup;
import com.nasmas.workouts.model.Users;
import com.nasmas.workouts.model.Workout;
import com.nasmas.workouts.model.WorkoutType;
import com.nasmas.workouts.model.local.WorkoutLocal;
import com.nasmas.workouts.model.local.WorkoutsListLocal;
import com.nasmas.workouts.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
    public String createWorkout(WorkoutLocal workoutLocal, ModelMap model, @RequestParam("page") Optional<Integer> page, @RequestParam("size")
            Optional<Integer> size) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) auth.getPrincipal();

        model = utilService.getBaseModel(model, page, size);

        List<MuscleGroup> muscleGroups = new ArrayList<>();
        for (String muscleGroupUuid : workoutLocal.getMuscleGroup()) {
            muscleGroups.add(muscleGroupService.getMuscleGroupByUuid(UUID.fromString(muscleGroupUuid)));
        }
        WorkoutType workoutType = workoutTypeService.getWorkoutTypeByUuid(UUID.fromString(workoutLocal.getWorkoutType()));
        Workout workout = new Workout(workoutLocal, workoutType, muscleGroups, usersService.findByName(userDetails.getUsername()));
        workoutService.createWorkout(workout);

        return "redirect:index";
    }

    @GetMapping("/workout/{uuid}")
    public String getWorkoutByUuid(@PathVariable String uuid, ModelMap model, @RequestParam("page") Optional<Integer> page, @RequestParam("size")
            Optional<Integer> size) {
        model = utilService.getBaseModel(model, page, size);
        Workout workout = workoutService.getWorkout(UUID.fromString(uuid));
        model.addAttribute("workout", workout);
        return "workout";
    }

    @GetMapping("/workouts/{couchId}")
    public ResponseEntity<WorkoutsListLocal> getWorkoutsByCouchUniqueId(@PathVariable Long couchId) {
        List<Workout> workoutsByCouch = workoutService.getWorkoutsList(couchId);
        WorkoutsListLocal workoutsListLocal = new WorkoutsListLocal(workoutsByCouch);
//        model.addAttribute("workoutsByCouch", workoutsByCouch);
        return new ResponseEntity<>(workoutsListLocal, HttpStatus.OK);
    }

//    @GetMapping("/workout/{uniqueId}")
//    public String getWorkout(@PathVariable Long uniqueId, ModelMap model) {
//
//        model = utilService.getBaseModel(model);
//        return "workout";
//    }
}
