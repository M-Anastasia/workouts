package com.nasmas.workouts.service;

import com.nasmas.workouts.model.Contraindication;
import com.nasmas.workouts.model.MuscleGroup;
import com.nasmas.workouts.model.Workout;
import com.nasmas.workouts.model.WorkoutType;
import com.nasmas.workouts.repository.WorkoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

@Service
public class WorkoutService {

    @Autowired
    private WorkoutRepository workoutRepository;
    @Autowired
    private ContraindicationService contraindicationService;
    @Autowired
    private MuscleGroupService muscleGroupService;
    @Autowired
    private WorkoutTypeService workoutTypeService;

    // 1 создание тренировки
    public Workout createWorkout(Workout workout, Set<Contraindication> contraindications,
                                 Set<MuscleGroup> muscleGroups, WorkoutType workoutType) {
        workout.setContraindication(contraindications);
        workout.setMuscleGroup(muscleGroups);
        workout.setWorkoutType(workoutType);
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
    public Workout updateWorkout(UUID workoutUuid, Workout workout, Set<Contraindication> contraindications,
                                 Set<MuscleGroup> muscleGroups, WorkoutType workoutType) {
        Workout workoutOld = workoutRepository.findByUuid(workoutUuid);
        workout.setUniqueId(workoutOld.getUniqueId());
        workout.setUuid(workoutUuid);
        return createWorkout(workout, contraindications, muscleGroups, workoutType);
    }

    // 5 получение рандомной тренировки по List<UUID> мышечной группы, UUID типа ренировки
    //  и исключая противопоказания(List<UUID>), если они есть
}
