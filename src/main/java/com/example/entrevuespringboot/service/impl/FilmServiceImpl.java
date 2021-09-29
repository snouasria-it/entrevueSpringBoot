package com.example.entrevuespringboot.service.impl;

import com.example.entrevuespringboot.exception.FilmNotFoundException;
import com.example.entrevuespringboot.entity.Film;
import com.example.entrevuespringboot.repository.FilmRepository;
import com.example.entrevuespringboot.service.FilmService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("filmService")
public class FilmServiceImpl implements FilmService {

    public static final Logger logger = LoggerFactory.getLogger(FilmServiceImpl.class);

    @Autowired
    FilmRepository filmRepository;

    @Override
    public Film findById(Long id) {
        return filmRepository.findById(id).orElseThrow(()->new FilmNotFoundException(id));
    }

    @Override
    public Film saveFilm(Film film) {
        return filmRepository.save(film);
    }

}
