package com.example.entrevuespringboot.service;

import com.example.entrevuespringboot.exception.FilmNotFoundException;
import com.example.entrevuespringboot.entity.Film;
import com.example.entrevuespringboot.repository.FilmRepository;
import com.example.entrevuespringboot.service.impl.FilmServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;

@SpringBootTest(classes = {FilmServiceImpl.class})
class FilmServiceImplTest {

    @MockBean
    private FilmRepository filmRepository;

    @InjectMocks
    @Autowired
    private FilmServiceImpl filmService;

    @Test
    public void findById_whenGivenId_shouldReturnFilm_ifFound() {

        Film film = new Film();
        film.setId(99L);

        Mockito.when(filmRepository.findById(film.getId())).thenReturn(Optional.of(film));
        Film expected = filmService.findById(film.getId());

        assertEquals(expected.getId(),film.getId());
        verify(filmRepository).findById(film.getId());
    }
    @Test
    public void findById_shouldThrowException_whenUserDoesntExist() {

        Film film = new Film();
        film.setId(99L);
        
        Mockito.when(filmRepository.findById(anyLong())).thenReturn(Optional.ofNullable(null));
        assertThrows(FilmNotFoundException.class, ()-> filmService.findById(film.getId()));
    }

    @Test
    void saveFilm_shouldReturnCreatedFilm() {

        Film film = new Film();
        film.setTitre("Titre test");

        Mockito.when(filmRepository.save(ArgumentMatchers.any(Film.class))).thenReturn(film);
        Film created = filmService.saveFilm(film);

        assertEquals(created.getTitre(),film.getTitre());
        verify(filmRepository).save(film);
    }
}