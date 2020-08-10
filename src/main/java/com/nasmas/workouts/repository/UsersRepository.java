package com.nasmas.workouts.repository;

import com.nasmas.workouts.model.Users;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends BaseRepository<Users, Long> {
}
