package com.nasmas.workouts.repository;

import com.nasmas.workouts.model.Users;
import com.nasmas.workouts.model.Workout;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends BaseRepository<Users, Long> {

    Users findByName(String name);

    @Query("select u from Users u where u.name = ?1")
    Users getByName(String name);
}
