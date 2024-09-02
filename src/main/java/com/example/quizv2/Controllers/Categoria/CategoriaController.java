package com.example.quizv2.Controllers.Categoria;

import com.example.quizv2.Models.Categoria.CategoriaRequest;
import com.example.quizv2.Models.Categoria.CategoriaResponse;
import com.example.quizv2.Services.Categoria.CategoriaService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categorias")
@AllArgsConstructor
public class CategoriaController {

    private final CategoriaService categoriaService;

    @GetMapping("findAll")
    public ResponseEntity<List<CategoriaResponse>> getAllCategorias() {
        List<CategoriaResponse> categorias = categoriaService.findAllCategorias();
        return ResponseEntity.ok(categorias);
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<CategoriaResponse> getCategoriaById(@PathVariable Long id) {
        CategoriaResponse categoria = categoriaService.findCategoriaById(id);
        return ResponseEntity.ok(categoria);
    }

    @PostMapping("/save")
    public ResponseEntity<CategoriaResponse> createCategoria(@RequestBody CategoriaRequest categoriaRequest) {
        CategoriaResponse createdCategoria = categoriaService.saveCategoria(categoriaRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCategoria);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CategoriaResponse> updateCategoria(@PathVariable Long id, @RequestBody CategoriaRequest categoriaRequest) {
        CategoriaResponse updatedCategoria = categoriaService.updateCategoria(id, categoriaRequest);
        return ResponseEntity.ok(updatedCategoria);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCategoria(@PathVariable Long id) {
        String response = categoriaService.deleteCategoria(id);
        if (response.equals("Categoria eliminada")) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
}
