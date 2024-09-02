package com.example.quizv2.Models.Pregunta;

import com.example.quizv2.Models.Categoria.CategoriaResponse;
import com.example.quizv2.Models.Respuesta.RespuestaResponse;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PreguntaResponse {

    private Long id;
    private String pregunta;

    @JsonIgnore
    private CategoriaResponse categoria;
    @JsonIgnore
    private List<RespuestaResponse> respuestaResponseList;
}
