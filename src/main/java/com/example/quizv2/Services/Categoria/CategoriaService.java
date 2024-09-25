package com.example.quizv2.Services.Categoria;

import com.example.quizv2.Models.Categoria.Categoria;
import com.example.quizv2.Models.Categoria.CategoriaRequest;
import com.example.quizv2.Models.Categoria.CategoriaResponse;
import com.example.quizv2.Models.Pregunta.PreguntaResponse;
import com.example.quizv2.Repositories.Categoria.CategoriaRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;
    private final ModelMapper modelMapper;

    public static final String CATEGORY_NOT_FOUND ="Categoria no encontrada";

    public List<CategoriaResponse> findAllCategorias() {
        List<Categoria> categorias = categoriaRepository.findAll();
        return categorias.stream()
                .map(categoria -> {
                    CategoriaResponse response = modelMapper.map(categoria, CategoriaResponse.class);
                    response.setPreguntas(categoria.getPreguntasList().stream()
                            .map(pregunta -> modelMapper.map(pregunta, PreguntaResponse.class))
                            .toList());
                    return response;
                })
                .toList();
    }

    public CategoriaResponse findCategoriaById(Long id) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(CATEGORY_NOT_FOUND));
        CategoriaResponse response = modelMapper.map(categoria, CategoriaResponse.class);
        response.setPreguntas(categoria.getPreguntasList().stream()
                .map(pregunta -> modelMapper.map(pregunta, PreguntaResponse.class))
                .toList());
        return response;
    }

    public CategoriaResponse saveCategoria(CategoriaRequest categoriaRequest) {
        Categoria categoria = modelMapper.map(categoriaRequest, Categoria.class);
        Categoria savedCategoria = categoriaRepository.save(categoria);
        return modelMapper.map(savedCategoria, CategoriaResponse.class);
    }

    public CategoriaResponse updateCategoria(Long id, CategoriaRequest categoriaRequest) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(CATEGORY_NOT_FOUND));
        modelMapper.map(categoriaRequest, categoria);
        Categoria updatedCategoria = categoriaRepository.save(categoria);
        return modelMapper.map(updatedCategoria, CategoriaResponse.class);
    }

    public String deleteCategoria(Long id) {
        if(categoriaRepository.existsById(id)) {
            categoriaRepository.deleteById(id);
            return "Categoria eliminada";
        } else {
            return CATEGORY_NOT_FOUND;
        }
    }
}
