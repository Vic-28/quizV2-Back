package com.example.quizv2.Controllers.Pregunta;

import com.example.quizv2.Models.Pregunta.PreguntaRequest;
import com.example.quizv2.Models.Pregunta.PreguntaResponse;
import com.example.quizv2.Services.Pregunta.PreguntaService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/preguntas")
@AllArgsConstructor
public class PreguntaController {

    private final PreguntaService preguntaService;

    @GetMapping
    public ResponseEntity<List<PreguntaResponse>> getAllPreguntas() {
        List<PreguntaResponse> preguntas = preguntaService.findAllPreguntas();
        return ResponseEntity.ok(preguntas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PreguntaResponse> getPreguntaById(@PathVariable Long id) {
        PreguntaResponse pregunta = preguntaService.findPreguntaById(id);
        return ResponseEntity.ok(pregunta);
    }

    @PostMapping
    public ResponseEntity<PreguntaResponse> createPregunta(@RequestBody PreguntaRequest preguntaRequest) {
        PreguntaResponse createdPregunta = preguntaService.savePregunta(preguntaRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPregunta);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PreguntaResponse> updatePregunta(@PathVariable Long id, @RequestBody PreguntaRequest preguntaRequest) {
        PreguntaResponse updatedPregunta = preguntaService.updatePregunta(id, preguntaRequest);
        return ResponseEntity.ok(updatedPregunta);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePregunta(@PathVariable Long id) {
        String response = preguntaService.deletePregunta(id);
        if (response.equals("Pregunta eliminada")) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
}
