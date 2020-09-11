package com.nasmas.workouts.model.local;

import com.nasmas.workouts.model.Workout;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class WorkoutsListLocal {

    private List<WorkoutLocal> workoutLocalList;

    public WorkoutsListLocal(List<Workout> workoutsByCouch) {
        workoutLocalList = new ArrayList<>();
        for (Workout workout : workoutsByCouch) {
            workoutLocalList.add(new WorkoutLocal(workout));
        }
    }
}
