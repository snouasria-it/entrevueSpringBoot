package com.example.entrevuespringboot.repository;

import com.example.entrevuespringboot.entity.Film;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmRepository extends JpaRepository<Film, Long> {
}
