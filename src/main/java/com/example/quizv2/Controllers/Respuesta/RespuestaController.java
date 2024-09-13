package com.example.quizv2.Controllers.Respuesta;

import com.example.quizv2.Models.Respuesta.RespuestaRequest;
import com.example.quizv2.Models.Respuesta.RespuestaResponse;
import com.example.quizv2.Services.Respuesta.RespuestaService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/respuestas")
@AllArgsConstructor
public class RespuestaController {

    private final RespuestaService respuestaService;

    @GetMapping("/findAll")
    public ResponseEntity<List<RespuestaResponse>> getAllRespuestas() {
        List<RespuestaResponse> respuestas = respuestaService.findAllRespuestas();
        return ResponseEntity.ok(respuestas);
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<RespuestaResponse> getRespuestaById(@PathVariable Long id) {
        RespuestaResponse respuesta = respuestaService.findRespuestaById(id);
        return ResponseEntity.ok(respuesta);
    }



    @PostMapping("/save")
    public ResponseEntity<RespuestaResponse> createRespuesta(@RequestBody RespuestaRequest respuestaRequest) {
        RespuestaResponse createdRespuesta = respuestaService.saveRespuesta(respuestaRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdRespuesta);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<RespuestaResponse> updateRespuesta(@PathVariable Long id, @RequestBody RespuestaRequest respuestaRequest) {
        RespuestaResponse updatedRespuesta = respuestaService.updateRespuesta(id, respuestaRequest);
        return ResponseEntity.ok(updatedRespuesta);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteRespuesta(@PathVariable Long id) {
        String response = respuestaService.deleteRespuesta(id);
        if (response.equals("Respuesta eliminada")) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
}
