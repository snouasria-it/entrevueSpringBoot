package com.example.entrevuespringboot.dto;

import com.example.entrevuespringboot.entity.Actor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FilmDto implements Serializable {

    private Long id;

    private String titre;

    private String description;

    private List<Actor> acteurs;

}

