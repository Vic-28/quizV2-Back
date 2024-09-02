package com.example.quizv2.Services.Pregunta;

import com.example.quizv2.Models.Categoria.CategoriaResponse;
import com.example.quizv2.Models.Pregunta.Pregunta;
import com.example.quizv2.Models.Pregunta.PreguntaRequest;
import com.example.quizv2.Models.Pregunta.PreguntaResponse;
import com.example.quizv2.Models.Respuesta.RespuestaResponse;
import com.example.quizv2.Repositories.Pregunta.PreguntaRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class PreguntaService {

    private final PreguntaRepository preguntaRepository;
    private final ModelMapper modelMapper;

    public List<PreguntaResponse> findAllPreguntas() {
        List<Pregunta> preguntas = preguntaRepository.findAll();
        return preguntas.stream()
                .map(pregunta -> {
                    PreguntaResponse response = modelMapper.map(pregunta, PreguntaResponse.class);

                    response.setRespuestaResponseList(pregunta.getRespuestas().stream()
                            .map(respuesta -> modelMapper.map(respuesta, RespuestaResponse.class))
                            .collect(Collectors.toList()));

                    if (pregunta.getCategoria() != null) {
                        response.setCategoria(modelMapper.map(pregunta.getCategoria(), CategoriaResponse.class));
                    } else {
                        response.setCategoria(null);
                    }

                    return response;
                })
                .collect(Collectors.toList());
    }

    public PreguntaResponse findPreguntaById(Long id) {
        Pregunta pregunta = preguntaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pregunta no encontrada"));
        return modelMapper.map(pregunta, PreguntaResponse.class);
    }

    public PreguntaResponse savePregunta(PreguntaRequest preguntaRequest) {
        Pregunta pregunta = modelMapper.map(preguntaRequest, Pregunta.class);
        Pregunta savedPregunta = preguntaRepository.save(pregunta);
        return modelMapper.map(savedPregunta, PreguntaResponse.class);
    }

    public PreguntaResponse updatePregunta(Long id, PreguntaRequest preguntaRequest) {
        Pregunta pregunta = preguntaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pregunta no encontrada"));
        modelMapper.map(preguntaRequest, pregunta);
        Pregunta updatedPregunta = preguntaRepository.save(pregunta);
        return modelMapper.map(updatedPregunta, PreguntaResponse.class);
    }

    public String deletePregunta(Long id) {
        if (preguntaRepository.existsById(id)) {
            preguntaRepository.deleteById(id);
            return "Pregunta eliminada";
        } else {
            return "Pregunta no encontrada";
        }
    }
}