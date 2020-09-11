package com.nasmas.workouts.service;

import com.nasmas.workouts.model.MuscleGroup;
import com.nasmas.workouts.model.Users;
import com.nasmas.workouts.model.Workout;
import com.nasmas.workouts.model.WorkoutType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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

    public ModelMap getBaseModel(ModelMap model, Optional<Integer> page, Optional<Integer> size) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        List<WorkoutType> workoutTypes = workoutTypeService.getWorkoutTypeList();
        List<MuscleGroup> muscleGroups = muscleGroupService.getMuscleGroupList();

        List<Workout> workouts = workoutService.getWorkoutsList();

        final int currentPage = page.orElse(1);
        final int pageSize = size.orElse(12);
        Collections.reverse(workouts);
        Page<Workout> workoutPage = workoutService.findPaginated(PageRequest.of(currentPage - 1, pageSize), workouts);

        model.addAttribute("workoutPage", workoutPage);

        int totalPages = workoutPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        if (!auth.getPrincipal().equals("anonymousUser")) {
            UserDetails userDetails = (UserDetails) auth.getPrincipal();
            Users user = userService.findByName(userDetails.getUsername());
//            List<Workout> workoutsByCouch = workoutService.getWorkoutsList(user);
            model.addAttribute("couchId", user.getUniqueId());
//            model.addAttribute("workoutsByCouch", workoutsByCouch);
            model.addAttribute("username", user.getName());
            model.addAttribute("workoutTypes", workoutTypes);
            model.addAttribute("muscleGroups", muscleGroups);
        }
        return model;
    }


}
