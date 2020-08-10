package com.nasmas.workouts.service;

import com.nasmas.workouts.model.WorkoutType;
import com.nasmas.workouts.repository.WorkoutTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class WorkoutTypeService {

    @Autowired
    private WorkoutTypeRepository workoutTypeRepository;

    public WorkoutType getWorkoutTypeByUuid(UUID uuid) {
        return workoutTypeRepository.findByUuid(uuid);
    }

    public List<WorkoutType> getWorkoutTypeList() {
        return workoutTypeRepository.findAll();
    }
}
