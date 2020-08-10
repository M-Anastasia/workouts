package com.nasmas.workouts.service;

import com.nasmas.workouts.model.MuscleGroup;
import com.nasmas.workouts.repository.MuscleGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class MuscleGroupService {

    @Autowired
    private MuscleGroupRepository muscleGroupRepository;

    public MuscleGroup getMuscleGroupByUuid(UUID uuid) {
        return muscleGroupRepository.findByUuid(uuid);
    }

    public List<MuscleGroup> getMuscleGroupList() {
        return muscleGroupRepository.findAll();
    }
}
