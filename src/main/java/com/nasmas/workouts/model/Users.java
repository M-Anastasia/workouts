package com.nasmas.workouts.model;

import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Table(name = "users")
@Data
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "unique_id")
    private Long uniqueId;

    @Column(name = "uuid")
    private UUID uuid;

    @Column(name = "name")
    private String name;
}
