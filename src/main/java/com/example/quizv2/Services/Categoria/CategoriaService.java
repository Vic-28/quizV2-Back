package com.example.quizv2.Services.Categoria;

import com.example.quizv2.Models.Categoria.Categoria;
import com.example.quizv2.Models.Categoria.CategoriaRequest;
import com.example.quizv2.Models.Categoria.CategoriaResponse;
import com.example.quizv2.Repositories.Categoria.CategoriaRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;
    private final ModelMapper modelMapper;

    public List<CategoriaResponse> findAllCategorias()
    {
        List<Categoria> categorias = categoriaRepository.findAll();
        return categorias.stream()
                .map(categoria -> modelMapper.map(categoria, CategoriaResponse.class))
                .collect(Collectors.toList());
    }

    public CategoriaResponse findCategoriaById(Long id) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria no encontrada"));
        return modelMapper.map(categoria, CategoriaResponse.class);
    }

    public CategoriaResponse saveCategoria(CategoriaRequest categoriaRequest) {
        Categoria categoria = modelMapper.map(categoriaRequest, Categoria.class);
        Categoria savedCategoria = categoriaRepository.save(categoria);
        return modelMapper.map(savedCategoria, CategoriaResponse.class);
    }

    public CategoriaResponse updateCategoria(Long id, CategoriaRequest categoriaRequest) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria no encontrada"));
        modelMapper.map(categoriaRequest, categoria);
        Categoria updatedCategoria = categoriaRepository.save(categoria);
        return modelMapper.map(updatedCategoria, CategoriaResponse.class);
    }

    public String deleteCategoria(Long id) {
        if(categoriaRepository.existsById(id)) {
            categoriaRepository.deleteById(id);
            return "Categoria eliminada";
        } else {
            return "Categoria no encontrada";
        }
    }
}
