package com.example.entrevuespringboot.controller;

import com.example.entrevuespringboot.entity.Actor;
import com.example.entrevuespringboot.entity.Film;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.WebApplicationContext;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DirtiesContext
class FilmControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    @Order(1)
    void getFilm_whenNoFilm_thenReturn404() throws Exception{

        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/api/film/{id}", "1"))
                .andDo(print()).andExpect(status().isNotFound());
    }


    @Test
    @Order(3)
    void getFilm_whenFilmExists_thenReturn200() throws Exception{

        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/api/film/{id}", "1"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.titre").value("Star Wars: The Empire Strikes Back"))
                .andExpect(jsonPath("$.description").value("Darth Vader is adamant about turning Luke Skywalker to the dark side."))
                .andExpect(jsonPath("$.acteurs[0].id").value("2"))
                .andExpect(jsonPath("$.acteurs[0].nom").value("Ford"))
                .andExpect(jsonPath("$.acteurs[0].prenom").value("Harrison"))
                .andExpect(jsonPath("$.acteurs[1].id").value("3"))
                .andExpect(jsonPath("$.acteurs[1].nom").value("Hamill"))
                .andExpect(jsonPath("$.acteurs[1].prenom").value("Mark"));
    }

    @Test
    @Order(2)
    void createFilm_shouldReturn201() throws Exception{

        Film film = new Film();
        List<Actor> acteurs = new ArrayList<>();

        acteurs.add(new Actor(null,"Ford","Harrison"));
        acteurs.add(new Actor(null, "Hamill","Mark"));

        film.setTitre("Star Wars: The Empire Strikes Back");
        film.setDescription("Darth Vader is adamant about turning Luke Skywalker to the dark side.");
        film.setActeurs(acteurs);

        this.mockMvc.perform(post("/api/film").contentType("application/json").characterEncoding("charset-utf8").content(objectMapper.writeValueAsString(film))).andDo(print())
                .andExpect(status().isCreated()).andExpect(content()
                .contentType("application/json"))
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.titre").value("Star Wars: The Empire Strikes Back"))
                .andExpect(jsonPath("$.description").value("Darth Vader is adamant about turning Luke Skywalker to the dark side."))
                .andExpect(jsonPath("$.acteurs[0].id").value("2"))
                .andExpect(jsonPath("$.acteurs[0].nom").value("Ford"))
                .andExpect(jsonPath("$.acteurs[0].prenom").value("Harrison"))
                .andExpect(jsonPath("$.acteurs[1].id").value("3"))
                .andExpect(jsonPath("$.acteurs[1].nom").value("Hamill"))
                .andExpect(jsonPath("$.acteurs[1].prenom").value("Mark"));
    }
}