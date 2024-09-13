package com.example.quizv2.Repositories.Pregunta;

import com.example.quizv2.Models.Pregunta.Pregunta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PreguntaRepository extends JpaRepository<Pregunta, Long> {
    List<Pregunta> findByCategoriaId(Long categoriaId);
}
