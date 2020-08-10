package com.nasmas.workouts.model;

import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;
import java.util.UUID;

@Table(name = "workout")
@Data
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

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "muscle_group_id")
    @Fetch(FetchMode.JOIN)
    private Set<MuscleGroup> muscleGroup;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "contraindication_id")
    @Fetch(FetchMode.JOIN)
    private Set<Contraindication> contraindication;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "workout_type_id")
    @Fetch(FetchMode.JOIN)
    private WorkoutType workoutType;

    @Column(name = "video_link")
    private String videoLink;

    @Column(name = "created_time")
    private Timestamp createdTime;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "creator")
    @Fetch(FetchMode.JOIN)
    private Users creator;

    @Column(name = "modified_time")
    private Timestamp modifiedTime;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "modifier")
    @Fetch(FetchMode.JOIN)
    private Users modifier;
}
