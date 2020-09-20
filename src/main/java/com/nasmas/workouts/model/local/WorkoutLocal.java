package com.nasmas.workouts.model.local;

import com.nasmas.workouts.model.MuscleGroup;
import com.nasmas.workouts.model.Workout;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorkoutLocal {

    private String uuid;
    private String name;
    private String description;
    private List<String> muscleGroup;
    private String workoutType;
    private String videoLink;
    private Boolean isPrivate;

    public WorkoutLocal(Workout workout) {
        this.muscleGroup = new ArrayList<>();
        for (MuscleGroup muscleGroup : workout.getMuscleGroups()) {
            this.muscleGroup.add(muscleGroup.getName());
        }
        setUuid(workout.getUuid().toString());
        setName(workout.getName());
        setDescription(workout.getDescription());
        setWorkoutType(workout.getWorkoutType().getName());
        setVideoLink(workout.getVideoLink());
        setIsPrivate(workout.getIsPrivate());
    }
}
