package com.nasmas.workouts.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "muscle_group")
@Data
public class MuscleGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "unique_id")
    private Long uniqueId;

    @Column(name = "uuid")
    private UUID uuid;

    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "muscleGroups")
    private List<Workout> workouts;
}
