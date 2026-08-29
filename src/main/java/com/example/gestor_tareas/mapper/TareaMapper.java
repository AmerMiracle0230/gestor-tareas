package com.example.gestor_tareas.mapper;

import org.springframework.stereotype.Component;

import com.example.gestor_tareas.domain.Tarea;
import com.example.gestor_tareas.dto.tarea.TareaCreateDTO;
import com.example.gestor_tareas.dto.tarea.TareaPatchDTO;
import com.example.gestor_tareas.dto.tarea.TareaResponseDTO;
import com.example.gestor_tareas.dto.tarea.TareaUpdateDTO;

@Component
public class TareaMapper {
	
	//Convertir un DTO una Entidad de una tarea
	public Tarea toEntity(TareaCreateDTO tareaDTO) {
		
		Tarea tarea = new Tarea();
		
		tarea.setTitulo(tareaDTO.getTitulo());
		tarea.setDescripcion(tareaDTO.getDescripcion());
		tarea.setEstado(tareaDTO.isEstado());
	
		return tarea;
	}
	
	//Convertir una Entidad a un DTO
	public TareaResponseDTO toResponseDTO(Tarea tarea) {
		
		return new TareaResponseDTO(
				tarea.getId(),
				tarea.getTitulo(),
				tarea.getDescripcion(),
				tarea.isEstado());
		
	}
	
	
	//Actualizar completa una entiedad 
	public void updateEntity(TareaUpdateDTO tareaDTO, Tarea tarea) {
		
		tarea.setTitulo(tareaDTO.getTitulo());
		tarea.setDescripcion(tareaDTO.getDescripcion());
		tarea.setEstado(tareaDTO.isEstado());
		
	}
	
	//Actualizar Parcialmente una entidad
	public void updateParcialEntity(TareaPatchDTO tareaDTO, Tarea tarea) {
		
		if(tareaDTO.getTitulo() != null) {
			tarea.setTitulo(tareaDTO.getTitulo());
		}
		
		if(tareaDTO.getDescripcion() != null) {
			tarea.setDescripcion(tareaDTO.getDescripcion());
		}
		
		if(tareaDTO.getEstado() != null) {
			tarea.setEstado(tareaDTO.getEstado());
		}
		
	}
	
}
