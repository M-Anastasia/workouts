package com.nasmas.workouts.model;

import com.nasmas.workouts.model.local.WorkoutLocal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "workout")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Workout {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "unique_id")
    private Long uniqueId;

    @Column(name = "uuid")
    private UUID uuid;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @ManyToMany
    @JoinTable(
            name = "workouts",
            joinColumns = @JoinColumn(name = "muscle_group_id"),
            inverseJoinColumns = @JoinColumn(name = "workout_id"))
    private List<MuscleGroup> muscleGroups;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "workout_type_id")
    @Fetch(FetchMode.JOIN)
    private WorkoutType workoutType;

    @Column(name = "video_link", unique = true)
    private String videoLink;

    @CreationTimestamp
    @Column(name = "created_time")
    private Timestamp createdTime;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "creator")
    @Fetch(FetchMode.JOIN)
    private Users creator;

    @UpdateTimestamp
    @Column(name = "modified_time")
    private Timestamp modifiedTime;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "modifier")
    @Fetch(FetchMode.JOIN)
    private Users modifier;

    public Workout(WorkoutLocal workoutLocal, WorkoutType workoutType, List<MuscleGroup> muscleGroups) {
        setUuid(UUID.randomUUID());
        setName(workoutLocal.getName());
        setDescription(workoutLocal.getDescription());
        setWorkoutType(workoutType);
        setMuscleGroups(muscleGroups);
        setVideoLink(workoutLocal.getVideoLink());
    }
}
