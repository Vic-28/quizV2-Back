package com.example.quizv2.Models.Pregunta;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PreguntaRequest {

    private String pregunta;

    private Long categoriaId;

    private List<RespuestaRequest> respuestaRequestList;
}
