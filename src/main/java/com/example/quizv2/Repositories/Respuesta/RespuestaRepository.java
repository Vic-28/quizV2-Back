package com.example.quizv2.Repositories.Respuesta;

import com.example.quizv2.Models.Respuesta.Respuesta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RespuestaRepository extends JpaRepository<Respuesta, Long> {
}
