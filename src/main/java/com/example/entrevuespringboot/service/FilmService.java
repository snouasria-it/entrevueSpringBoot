package com.example.entrevuespringboot.service;

import com.example.entrevuespringboot.entity.Film;


public interface FilmService {
    Film findById(Long id);

    Film saveFilm(Film film);
}
