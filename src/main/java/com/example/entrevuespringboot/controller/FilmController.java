package com.example.entrevuespringboot.controller;

import com.example.entrevuespringboot.dto.FilmDto;
import com.example.entrevuespringboot.entity.Film;
import com.example.entrevuespringboot.mapper.MapStructMapper;
import com.example.entrevuespringboot.service.FilmService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/api")
public class FilmController {

    public static final Logger logger = LoggerFactory.getLogger(FilmController.class);

    @Autowired
    @Qualifier("filmService")
    FilmService filmService;

    @Autowired
    MapStructMapper mapStructMapper;

    @GetMapping("/film/{id}")
    public ResponseEntity<FilmDto> getFilm(@PathVariable("id") long id) {

        logger.info("Fetching Film with id {}", id);

        Film film = filmService.findById(id);
        if (film == null) {
            logger.error("Film with id {} not found.", id);
            return new ResponseEntity(new String("Film with id " + id
                    + " not found"), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<FilmDto>(mapStructMapper.filmToFilmDto(film), HttpStatus.OK);

    }


    @PostMapping("/film")
    public ResponseEntity<FilmDto> createFilm(@RequestBody FilmDto filmDto, UriComponentsBuilder ucBuilder) {

        logger.info("Creating Film : {}", filmDto);

        /**        Verification abondonnée car non requise, aussi isFilmExist() abondonnée de l'interface du service FilmService
         * if (filmService.isFilmExist(film)) {
         *   logger.error("Unable to create. A Film with name {} already exist", film.getTitre());
         *   return new ResponseEntity(new String("Unable to create. A Film with name " +
         *   film.getTitre() + " already exist."),HttpStatus.CONFLICT);
        }*/

        Film film = filmService.saveFilm(mapStructMapper.filmDtoToFilm(filmDto) );

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/api/films/{id}").buildAndExpand(film.getId()).toUri());

        return new ResponseEntity<FilmDto>(mapStructMapper.filmToFilmDto(film),headers,HttpStatus.CREATED);

    }

}
