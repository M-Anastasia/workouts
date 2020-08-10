package com.nasmas.workouts.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.UUID;

@NoRepositoryBean
public interface BaseRepository<T, ID> extends JpaRepository<T, ID> {

    T findByUniqueId(ID uniqueId);

    T findByUuid(UUID uuid);

    void deleteByUniqueId(ID uniqueId);

    void deleteByUuid(UUID uuid);
}
