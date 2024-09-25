package com.example.quizv2.Services.Respuesta;

import com.example.quizv2.Models.Respuesta.Respuesta;
import com.example.quizv2.Models.Respuesta.RespuestaRequest;
import com.example.quizv2.Models.Respuesta.RespuestaResponse;
import com.example.quizv2.Repositories.Respuesta.RespuestaRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RespuestaService {

    private final RespuestaRepository respuestaRepository;
    private final ModelMapper modelMapper;


    public static final String NOT_FOUND_ANSWER ="Respuesta no encontrada";

    public List<RespuestaResponse> findAllRespuestas() {
        List<Respuesta> respuestas = respuestaRepository.findAll();
        return respuestas.stream()
                .map(respuesta -> modelMapper.map(respuesta, RespuestaResponse.class))
                .toList();
    }

    public RespuestaResponse findRespuestaById(Long id) {
        Respuesta respuesta = respuestaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(NOT_FOUND_ANSWER));
        return modelMapper.map(respuesta, RespuestaResponse.class);
    }

    public RespuestaResponse saveRespuesta(RespuestaRequest respuestaRequest) {
        Respuesta respuesta = modelMapper.map(respuestaRequest, Respuesta.class);
        Respuesta savedRespuesta = respuestaRepository.save(respuesta);
        return modelMapper.map(savedRespuesta, RespuestaResponse.class);
    }

    public RespuestaResponse updateRespuesta(Long id, RespuestaRequest respuestaRequest) {
        Respuesta respuesta = respuestaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(NOT_FOUND_ANSWER));
        modelMapper.map(respuestaRequest, respuesta);
        Respuesta updatedRespuesta = respuestaRepository.save(respuesta);
        return modelMapper.map(updatedRespuesta, RespuestaResponse.class);
    }

    public String deleteRespuesta(Long id) {
        if (respuestaRepository.existsById(id)) {
            respuestaRepository.deleteById(id);
            return "Respuesta eliminada";
        } else {
            return NOT_FOUND_ANSWER;
        }
    }
}
