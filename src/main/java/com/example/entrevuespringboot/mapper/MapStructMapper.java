package com.example.entrevuespringboot.mapper;

import com.example.entrevuespringboot.dto.ActorDto;
import com.example.entrevuespringboot.dto.FilmDto;
import com.example.entrevuespringboot.entity.Actor;
import com.example.entrevuespringboot.entity.Film;
import org.mapstruct.Mapper;

@Mapper(
        componentModel = "spring"
)
public interface MapStructMapper {

    Film filmDtoToFilm(FilmDto filmDto);

    FilmDto filmToFilmDto(Film film);

}
