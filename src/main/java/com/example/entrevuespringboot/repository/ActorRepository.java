package com.example.entrevuespringboot.repository;

import com.example.entrevuespringboot.entity.Actor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActorRepository extends JpaRepository<Actor, Long> {
}
