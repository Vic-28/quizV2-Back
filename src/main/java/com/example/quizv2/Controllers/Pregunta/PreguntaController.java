package com.example.quizv2.Controllers.Pregunta;

import com.example.quizv2.Models.Pregunta.PreguntaRequest;
import com.example.quizv2.Models.Pregunta.PreguntaResponse;
import com.example.quizv2.Services.Pregunta.PreguntaService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/preguntas")
@AllArgsConstructor
public class PreguntaController {

    private final PreguntaService preguntaService;

    @GetMapping("/findAll")
    public ResponseEntity<List<PreguntaResponse>> getAllPreguntas() {
        List<PreguntaResponse> preguntas = preguntaService.findAllPreguntas();
        return ResponseEntity.ok(preguntas);
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<PreguntaResponse> getPreguntaById(@PathVariable Long id) {
        PreguntaResponse pregunta = preguntaService.findPreguntaById(id);
        return ResponseEntity.ok(pregunta);
    }

    @GetMapping("/findPreguntasByCategoryId/{id}")
    public ResponseEntity<List<PreguntaResponse>> findPreguntasByCategoryId(@PathVariable Long id) {
        List<PreguntaResponse> preguntas = preguntaService.findPreguntasByCategoryId(id);
        return ResponseEntity.ok(preguntas);
    }

    @PostMapping("/save")
    public ResponseEntity<PreguntaResponse> createPregunta(@RequestBody PreguntaRequest preguntaRequest) {
        PreguntaResponse createdPregunta = preguntaService.savePregunta(preguntaRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPregunta);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<PreguntaResponse> updatePregunta(@PathVariable Long id, @RequestBody @Validated PreguntaRequest preguntaRequest) {
        PreguntaResponse updatedPregunta = preguntaService.updatePregunta(id, preguntaRequest);
        return ResponseEntity.ok(updatedPregunta);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deletePregunta(@PathVariable Long id) {
        preguntaService.deletePregunta(id);
        return ResponseEntity.noContent().build();
    }
}

