package com.nasmas.workouts.repository;

import com.nasmas.workouts.model.WorkoutType;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkoutTypeRepository extends BaseRepository<WorkoutType, Long> {
}
