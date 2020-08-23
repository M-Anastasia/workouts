package com.nasmas.workouts.repository;

import com.nasmas.workouts.model.Users;
import com.nasmas.workouts.model.Workout;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkoutRepository extends BaseRepository<Workout, Long> {

    List<Workout> findAllByIsPrivateFalse();
    List<Workout> findAllByCreator(Users creator);
}
