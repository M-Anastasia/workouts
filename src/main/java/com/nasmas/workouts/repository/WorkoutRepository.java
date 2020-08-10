package com.nasmas.workouts.repository;

import com.nasmas.workouts.model.Workout;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkoutRepository extends BaseRepository<Workout, Long> {
}
