package com.example.entrevuespringboot.repository;

import com.example.entrevuespringboot.entity.Actor;
import com.example.entrevuespringboot.entity.Film;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.DirtiesContext;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class FilmRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private FilmRepository filmRepository;

    @Test
    @DirtiesContext
    public void whenFindById() {

        //given
        Actor actor1 = new Actor(null, "nom1", "prenom1");
        Actor actor2 = new Actor(null, "nom2", "prenom2");
        Film film = new Film(null,"Un Titre","Une Description", Arrays.asList(actor1,actor2));

        entityManager.persist(film);
        entityManager.flush();

        //when
        Film testFilm = filmRepository.findById(film.getId()).orElse(null);

        //then
        assertEquals(testFilm.getTitre(), film.getTitre());
        assertEquals(testFilm.getActeurs().size(),film.getActeurs().size());
    }
}