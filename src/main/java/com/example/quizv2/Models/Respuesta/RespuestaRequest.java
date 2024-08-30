package com.example.quizv2.Models.Respuesta;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RespuestaRequest {

    private String respuesta;
    private Boolean correcta;
}
