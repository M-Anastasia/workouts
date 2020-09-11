package com.nasmas.workouts.service;

import com.nasmas.workouts.model.MuscleGroup;
import com.nasmas.workouts.model.Users;
import com.nasmas.workouts.model.Workout;
import com.nasmas.workouts.model.WorkoutType;
import com.nasmas.workouts.repository.WorkoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
public class WorkoutService {

    @Autowired
    private WorkoutRepository workoutRepository;
    @Autowired
    private MuscleGroupService muscleGroupService;
    @Autowired
    private WorkoutTypeService workoutTypeService;
    @Autowired
    private UsersService usersService;

    // 1 создание тренировки
    public Workout createWorkout(Workout workout) {
        return workoutRepository.save(workout);
    }

    // 2 получение тренировки
    public Workout getWorkout(UUID uuid) {
        return workoutRepository.findByUuid(uuid);
    }

    // 3 удаление тренировки
    public void deleteWorkout(UUID uuid) {
    }

    // 4 обновление тренировки
    public Workout updateWorkout(UUID workoutUuid, Workout workout,
                                 Set<MuscleGroup> muscleGroups, WorkoutType workoutType) {
        Workout workoutOld = workoutRepository.findByUuid(workoutUuid);
        workout.setUniqueId(workoutOld.getUniqueId());
        workout.setUuid(workoutUuid);
        return createWorkout(workout);
    }

    public List<Workout> getWorkoutsList() {
        return workoutRepository.findAllByIsPrivateFalse();
    }

    public List<Workout> getWorkoutsList(Users user) {
        return workoutRepository.findAllByCreator(user);
    }

    public List<Workout> getWorkoutsList(Long couchUniqueId) {
        Users user = usersService.findUserById(couchUniqueId);
        return workoutRepository.findAllByCreator(user);
    }

    public Page<Workout> findPaginated(Pageable pageable, List<Workout> workouts) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<Workout> list;

        if (workouts.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, workouts.size());
            list = workouts.subList(startItem, toIndex);
        }

        Page<Workout> workoutPage = new PageImpl<>(list, PageRequest.of(currentPage, pageSize), workouts.size());

        return workoutPage;
    }

    // 5 получение рандомной тренировки по List<UUID> мышечной группы, UUID типа ренировки
    //  и исключая противопоказания(List<UUID>), если они есть
}
