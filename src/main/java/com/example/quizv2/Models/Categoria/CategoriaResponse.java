package com.example.quizv2.Models.Categoria;

import com.example.quizv2.Models.Pregunta.PreguntaResponse;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoriaResponse {

    private Long id;
    private String nombre;
    private String descripcion;

    @JsonIgnore
    private List<PreguntaResponse> preguntas;
}
