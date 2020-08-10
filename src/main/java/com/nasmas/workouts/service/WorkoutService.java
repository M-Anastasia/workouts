package com.nasmas.workouts.service;

import com.nasmas.workouts.repository.WorkoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    // 2 получение тренировки
    // 3 удаление тренировки
    // 4 обновление тренировки

    // 5 получение рандомной тренировки по List<UUID> мышечной группы, UUID типа ренировки
    //  и исключая противопоказания(List<UUID>), если они есть
}
