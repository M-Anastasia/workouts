package com.nasmas.workouts.model.local;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorkoutLocal {

    private String name;
    private String description;
    private Set<String> muscleGroup;
    private String workoutType;
    private String videoLink;
    private Boolean isPrivate;
}
