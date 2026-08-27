package com.example.gestor_tareas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.gestor_tareas.domain.Tarea;

public interface TareaRepository extends JpaRepository<Tarea, Long>{
	List <Tarea> findByUsuarioId(Long id);
}
